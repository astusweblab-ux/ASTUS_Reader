package com.astus.reader.core.util

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.nio.charset.Charset
import java.util.Locale
import java.util.UUID
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.Element
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.text.PDFTextStripper

data class ParsedBook(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val author: String,
    val coverUri: String?,
    val sourceUri: String,
    val format: String,
    val content: String,
    val importedAt: Long = System.currentTimeMillis()
)

class UnsupportedBookFormatException(format: String) : IllegalArgumentException(
    "Формат .$format не поддерживается. Поддерживаются TXT, FB2, FB2.ZIP, EPUB, PDF, DOCX, HTML, MD и RTF."
)

object BookParser {
    fun parse(context: Context, uri: Uri, id: String = UUID.randomUUID().toString()): ParsedBook {
        val contentResolver = context.contentResolver
        val displayName = displayName(contentResolver, uri) ?: uri.lastPathSegment ?: "Book"
        val format = displayName.substringAfterLast('.', "").lowercase(Locale.ROOT).ifBlank { "txt" }
        val bytes = contentResolver.openInputStream(uri).use { input ->
            requireNotNull(input) { "Cannot open $uri" }.readBytes()
        }

        val parsed = when (format) {
            "txt", "text" -> ParsedText(titleFromName(displayName), "Unknown author", readText(bytes))
            "md", "markdown" -> ParsedText(titleFromName(displayName), "Unknown author", parseMarkdown(bytes))
            "html", "htm", "xhtml" -> ParsedText(titleFromName(displayName), "Unknown author", readText(bytes).stripTags())
            "rtf" -> ParsedText(titleFromName(displayName), "Unknown author", parseRtf(bytes))
            "fb2" -> parseFb2(bytes, displayName)
            "epub" -> parseEpub(bytes, displayName)
            "zip" -> parseZipBook(bytes, displayName)
            "pdf" -> parsePdf(context, bytes, displayName)
            "docx" -> parseDocx(bytes, displayName)
            else -> throw UnsupportedBookFormatException(format)
        }

        return ParsedBook(
            id = id,
            title = parsed.title,
            author = parsed.author,
            coverUri = saveCover(context, id, parsed.cover),
            sourceUri = uri.toString(),
            format = format.uppercase(Locale.ROOT),
            content = parsed.content
        )
    }

    private fun displayName(contentResolver: ContentResolver, uri: Uri): String? {
        val cursor: Cursor? = contentResolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)
        return cursor.use {
            if (it != null && it.moveToFirst()) it.getString(0) else null
        }
    }

    private fun parseFb2(bytes: ByteArray, fallbackName: String): ParsedText {
        return runCatching {
            val factory = DocumentBuilderFactory.newInstance().apply {
                isNamespaceAware = false
                isIgnoringComments = true
                setFeature("http://apache.org/xml/features/disallow-doctype-decl", true)
                setFeature("http://xml.org/sax/features/external-general-entities", false)
                setFeature("http://xml.org/sax/features/external-parameter-entities", false)
                setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
                isExpandEntityReferences = false
            }
            val document = factory.newDocumentBuilder().parse(bytes.inputStream())
            val title = document.getElementsByTagName("book-title").item(0)?.textContent?.trim()
                ?: titleFromName(fallbackName)
            val authorElement = document.getElementsByTagName("author").item(0) as? Element
            val author = authorElement?.let {
                listOf("first-name", "middle-name", "last-name")
                    .mapNotNull { tag -> it.getElementsByTagName(tag).item(0)?.textContent?.trim() }
                    .joinToString(" ")
                    .ifBlank { null }
            } ?: "Unknown author"
            val body = document.getElementsByTagName("body").item(0)?.textContent ?: readText(bytes)
            ParsedText(
                title = title,
                author = author,
                content = cleanWhitespace(body),
                cover = extractFb2Cover(document.documentElement) ?: extractFb2CoverFromText(readText(bytes))
            )
        }.getOrElse {
            val text = readText(bytes)
            ParsedText(titleFromName(fallbackName), "Unknown author", text.stripTags(), extractFb2CoverFromText(text))
        }
    }

    private fun parseZipBook(bytes: ByteArray, fallbackName: String): ParsedText {
        var firstSupported: ParsedText? = null
        ZipInputStream(bytes.inputStream()).use { zip ->
            var entry: ZipEntry? = zip.nextEntry
            while (entry != null) {
                if (!entry.isDirectory) {
                    val name = entry.name.lowercase(Locale.ROOT)
                    val data = zip.readCurrentEntry()
                    firstSupported = when {
                        name.endsWith(".fb2") -> parseFb2(data, fallbackName.substringBeforeLast(".zip"))
                        name.endsWith(".txt") -> ParsedText(titleFromName(entry.name), "Unknown author", readText(data))
                        name.endsWith(".html") || name.endsWith(".htm") || name.endsWith(".xhtml") ->
                            ParsedText(titleFromName(entry.name), "Unknown author", readText(data).stripTags())
                        else -> firstSupported
                    }
                    if (firstSupported != null) return firstSupported
                }
                zip.closeEntry()
                entry = zip.nextEntry
            }
        }
        return runCatching { parseEpub(bytes, fallbackName) }
            .getOrElse { throw UnsupportedBookFormatException("zip") }
    }

    private fun parsePdf(context: Context, bytes: ByteArray, fallbackName: String): ParsedText {
        PDFBoxResourceLoader.init(context)
        val text = PDDocument.load(bytes).use { document ->
            PDFTextStripper().getText(document)
        }
        val cleaned = cleanWhitespace(text)
        if (cleaned.isBlank()) {
            throw IllegalArgumentException("PDF не содержит извлекаемого текста. Возможно, это сканы страниц.")
        }
        return ParsedText(titleFromName(fallbackName), "Unknown author", cleaned)
    }

    private fun parseDocx(bytes: ByteArray, fallbackName: String): ParsedText {
        var documentXml: String? = null
        ZipInputStream(bytes.inputStream()).use { zip ->
            var entry: ZipEntry? = zip.nextEntry
            while (entry != null) {
                if (!entry.isDirectory && entry.name.equals("word/document.xml", ignoreCase = true)) {
                    documentXml = readText(zip.readCurrentEntry())
                    break
                }
                zip.closeEntry()
                entry = zip.nextEntry
            }
        }
        val content = documentXml
            ?.replace("</w:p>", "\n")
            ?.stripTags()
            ?.let(::cleanWhitespace)
            .orEmpty()
        if (content.isBlank()) throw IllegalArgumentException("DOCX не содержит извлекаемого текста.")
        return ParsedText(titleFromName(fallbackName), "Unknown author", content)
    }

    private fun parseEpub(bytes: ByteArray, fallbackName: String): ParsedText {
        val files = linkedMapOf<String, ByteArray>()
        var title: String? = null
        var author: String? = null
        ZipInputStream(bytes.inputStream()).use { zip ->
            generateSequence { zip.nextEntry }.forEach { entry ->
                if (!entry.isDirectory) {
                    files[entry.name] = zip.readCurrentEntry()
                }
                zip.closeEntry()
            }
        }
        val chapters = mutableListOf<String>()
        val opfEntry = files.entries.firstOrNull { it.key.lowercase(Locale.ROOT).endsWith(".opf") }
        val opf = opfEntry?.value?.let(::readText)
        if (opf != null) {
            title = Regex("<dc:title[^>]*>(.*?)</dc:title>", RegexOption.IGNORE_CASE)
                .find(opf)?.groupValues?.getOrNull(1)?.stripTags()
            author = Regex("<dc:creator[^>]*>(.*?)</dc:creator>", RegexOption.IGNORE_CASE)
                .find(opf)?.groupValues?.getOrNull(1)?.stripTags()
        }
        files.forEach { (path, data) ->
            val name = path.lowercase(Locale.ROOT)
            if (name.endsWith(".xhtml") || name.endsWith(".html") || name.endsWith(".htm")) {
                chapters += readText(data).stripTags()
            }
        }
        return ParsedText(
            title = title?.ifBlank { null } ?: titleFromName(fallbackName),
            author = author?.ifBlank { null } ?: "Unknown author",
            content = cleanWhitespace(chapters.joinToString("\n\n")).ifBlank { titleFromName(fallbackName) },
            cover = extractEpubCover(files, opfEntry?.key, opf)
        )
    }

    private fun extractFb2Cover(root: Element): CoverImage? {
        val cover = root.getElementsByTagName("coverpage").item(0) as? Element ?: return null
        val image = cover.getElementsByTagName("image").item(0) as? Element ?: return null
        val href = listOf("l:href", "xlink:href", "href")
            .firstNotNullOfOrNull { attr -> image.getAttribute(attr).takeIf { it.isNotBlank() } }
            ?.removePrefix("#")
            ?: return null
        val binaries = root.getElementsByTagName("binary")
        for (index in 0 until binaries.length) {
            val binary = binaries.item(index) as? Element ?: continue
            if (binary.getAttribute("id") == href) {
                val contentType = binary.getAttribute("content-type")
                val extension = extensionFromMime(contentType)
                val data = Base64.decode(binary.textContent.replace(Regex("\\s+"), ""), Base64.DEFAULT)
                return CoverImage(data, extension)
            }
        }
        return null
    }

    private fun extractFb2CoverFromText(text: String): CoverImage? {
        val coverBlock = Regex("<coverpage[\\s\\S]*?</coverpage>", RegexOption.IGNORE_CASE)
            .find(text)?.value ?: return null
        val href = Regex("(?:l:href|xlink:href|href)=[\"']#?([^\"']+)[\"']", RegexOption.IGNORE_CASE)
            .find(coverBlock)?.groupValues?.getOrNull(1) ?: return null
        val binaryRegex = Regex(
            "<binary\\b(?=[^>]*\\bid=[\"']${Regex.escape(href)}[\"'])(?=[^>]*\\bcontent-type=[\"']([^\"']+)[\"'])[^>]*>([\\s\\S]*?)</binary>",
            RegexOption.IGNORE_CASE
        )
        val match = binaryRegex.find(text) ?: return null
        val contentType = match.groupValues.getOrNull(1).orEmpty()
        val base64 = match.groupValues.getOrNull(2).orEmpty().replace(Regex("\\s+"), "")
        return runCatching {
            CoverImage(Base64.decode(base64, Base64.DEFAULT), extensionFromMime(contentType))
        }.getOrNull()
    }

    private fun extractEpubCover(files: Map<String, ByteArray>, opfPath: String?, opf: String?): CoverImage? {
        val imagePaths = files.keys.filter { it.isImagePath() }
        val coverHref = opf?.let { metadata ->
            val coverId = Regex("<meta[^>]+name=[\"']cover[\"'][^>]+content=[\"']([^\"']+)[\"']", RegexOption.IGNORE_CASE)
                .find(metadata)?.groupValues?.getOrNull(1)
            when {
                coverId != null -> Regex("<item[^>]+id=[\"']${Regex.escape(coverId)}[\"'][^>]+href=[\"']([^\"']+)[\"']", RegexOption.IGNORE_CASE)
                    .find(metadata)?.groupValues?.getOrNull(1)
                else -> Regex("<item[^>]+properties=[\"'][^\"']*cover-image[^\"']*[\"'][^>]+href=[\"']([^\"']+)[\"']", RegexOption.IGNORE_CASE)
                    .find(metadata)?.groupValues?.getOrNull(1)
            }
        }
        val resolvedCoverPath = coverHref?.let { resolveEpubPath(opfPath, it) }
        val coverPath = imagePaths.firstOrNull { it.equals(resolvedCoverPath, ignoreCase = true) }
            ?: imagePaths.firstOrNull { it.contains("cover", ignoreCase = true) }
            ?: imagePaths.firstOrNull()
            ?: return null
        return CoverImage(files.getValue(coverPath), coverPath.substringAfterLast('.', "jpg"))
    }

    private fun ZipInputStream.readCurrentEntry(): ByteArray {
        val out = ByteArrayOutputStream()
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        while (true) {
            val read = read(buffer)
            if (read <= 0) break
            out.write(buffer, 0, read)
        }
        return out.toByteArray()
    }

    private fun readText(bytes: ByteArray): String =
        runCatching { bytes.toString(Charsets.UTF_8) }
            .getOrElse { bytes.toString(Charset.defaultCharset()) }

    private fun parseMarkdown(bytes: ByteArray): String =
        cleanWhitespace(
            readText(bytes)
                .replace(Regex("```[\\s\\S]*?```"), " ")
                .replace(Regex("!\\[[^]]*]\\([^)]*\\)"), " ")
                .replace(Regex("\\[([^]]+)]\\([^)]*\\)"), "$1")
                .replace(Regex("[#>*_`~-]+"), " ")
        )

    private fun parseRtf(bytes: ByteArray): String =
        cleanWhitespace(
            readText(bytes)
                .replace(Regex("\\\\'[0-9a-fA-F]{2}"), " ")
                .replace(Regex("\\\\[a-zA-Z]+-?\\d* ?"), " ")
                .replace(Regex("[{}]"), " ")
        )

    private fun InputStream.readBytes(): ByteArray {
        val out = ByteArrayOutputStream()
        copyTo(out)
        return out.toByteArray()
    }

    private fun titleFromName(name: String): String =
        name.substringBeforeLast('.').replace('_', ' ').trim().ifBlank { "Untitled book" }

    private fun saveCover(context: Context, bookId: String, cover: CoverImage?): String? {
        if (cover == null || cover.bytes.isEmpty()) return null
        val directory = File(context.filesDir, "covers").apply { mkdirs() }
        val extension = cover.extension.lowercase(Locale.ROOT).ifBlank { "jpg" }
        val file = File(directory, "$bookId.$extension")
        file.writeBytes(cover.bytes)
        return Uri.fromFile(file).toString()
    }

    private fun resolveEpubPath(opfPath: String?, href: String): String {
        val base = opfPath?.substringBeforeLast('/', missingDelimiterValue = "").orEmpty()
        return if (base.isBlank()) href else "$base/$href"
    }

    private fun String.isImagePath(): Boolean {
        val lower = lowercase(Locale.ROOT)
        return lower.endsWith(".jpg") || lower.endsWith(".jpeg") ||
            lower.endsWith(".png") || lower.endsWith(".webp")
    }

    private fun extensionFromMime(mime: String): String =
        when (mime.lowercase(Locale.ROOT)) {
            "image/png" -> "png"
            "image/webp" -> "webp"
            "image/jpeg", "image/jpg" -> "jpg"
            else -> "jpg"
        }

    private fun String.stripTags(): String =
        replace(Regex("<script[\\s\\S]*?</script>", RegexOption.IGNORE_CASE), " ")
            .replace(Regex("<style[\\s\\S]*?</style>", RegexOption.IGNORE_CASE), " ")
            .replace(Regex("<[^>]+>"), " ")
            .replace("&nbsp;", " ")
            .replace("&amp;", "&")
            .replace("&quot;", "\"")
            .replace("&apos;", "'")
            .replace("&lt;", "<")
            .replace("&gt;", ">")
            .trim()

    private fun cleanWhitespace(text: String): String =
        text.replace("\r", "\n")
            .replace(Regex("[ \\t]+"), " ")
            .replace(Regex("\\n{3,}"), "\n\n")
            .trim()

    private data class ParsedText(
        val title: String,
        val author: String,
        val content: String,
        val cover: CoverImage? = null
    )

    private data class CoverImage(
        val bytes: ByteArray,
        val extension: String
    )
}
