package com.astus.reader.core.util

data class SentenceRange(
    val index: Int,
    val start: Int,
    val end: Int,
    val text: String
)

object TextChunker {
    private val sentenceEnd = Regex("""(?<=[.!?…])\s+""")

    fun sentences(text: String): List<SentenceRange> {
        if (text.isBlank()) return emptyList()
        var cursor = 0
        return sentenceEnd.split(text)
            .asSequence()
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .mapIndexed { index, sentence ->
                val start = text.indexOf(sentence, cursor).coerceAtLeast(cursor)
                val end = (start + sentence.length).coerceAtMost(text.length)
                cursor = end
                SentenceRange(index, start, end, sentence)
            }
            .toList()
    }

    fun previewAt(text: String, position: Int): String {
        val safePosition = position.coerceIn(0, text.length)
        val end = (safePosition + 90).coerceAtMost(text.length)
        return text.substring(safePosition, end)
            .replace(Regex("\\s+"), " ")
            .trim()
            .ifBlank { "Bookmark" }
    }
}
