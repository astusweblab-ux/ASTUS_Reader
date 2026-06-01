package com.astus.reader.data

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri
import com.astus.reader.core.database.dao.BookDao
import com.astus.reader.core.database.entity.BookmarkEntity
import com.astus.reader.core.datastore.SettingsDataStore
import com.astus.reader.core.util.BookParser
import com.astus.reader.core.util.UnsupportedBookFormatException
import com.astus.reader.domain.model.Book
import com.astus.reader.domain.model.Bookmark
import com.astus.reader.domain.model.ReadingProgress
import com.astus.reader.domain.model.TtsProgress
import com.astus.reader.domain.repository.BookRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

@Singleton
class BookRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dao: BookDao,
    private val settingsDataStore: SettingsDataStore
) : BookRepository {
    override fun observeBooks(): Flow<List<Book>> =
        dao.observeBooks().map { books -> books.map { it.toDomain() } }

    override fun observeBook(bookId: String): Flow<Book?> =
        dao.observeBook(bookId).map { it?.toDomain() }

    override fun observeBookmarks(bookId: String): Flow<List<Bookmark>> =
        dao.observeBookmarks(bookId).map { bookmarks -> bookmarks.map { it.toDomain() } }

    override fun observeReadingProgress(bookId: String): Flow<ReadingProgress?> =
        dao.observeReadingProgress(bookId).map { it?.toDomain() }

    override fun observeTtsProgress(bookId: String): Flow<TtsProgress?> =
        dao.observeTtsProgress(bookId).map { it?.toDomain() }

    override suspend fun getBook(bookId: String): Book? =
        dao.getBook(bookId)?.toDomain()

    override suspend fun importBooks(uris: List<Uri>): List<Book> = withContext(Dispatchers.IO) {
        val failures = mutableListOf<String>()
        val parsedBooks = uris.mapNotNull { uri ->
            runCatching {
                context.contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }
            runCatching { BookParser.parse(context, uri) }
                .onFailure { error ->
                    failures += when (error) {
                        is UnsupportedBookFormatException -> error.message ?: "Формат файла не поддерживается."
                        else -> "Не удалось импортировать файл: ${error.message ?: "ошибка чтения"}"
                    }
                }
                .getOrNull()
        }
        dao.upsertBooks(parsedBooks.map { it.toEntity() })
        parsedBooks.firstOrNull()?.let { settingsDataStore.setLastBook(it.id) }
        if (failures.isNotEmpty()) {
            val prefix = if (parsedBooks.isEmpty()) "" else "Часть файлов импортирована. "
            throw IllegalArgumentException(prefix + failures.distinct().joinToString("\n"))
        }
        parsedBooks.map { it.toEntity().toDomain() }
    }

    override suspend fun refreshMissingCovers(books: List<Book>) = withContext(Dispatchers.IO) {
        books.filter { it.coverUri == null && it.sourceUri.isNotBlank() }
            .forEach { book ->
                val coverUri = runCatching {
                    BookParser.parse(context, book.sourceUri.toUri(), book.id).coverUri
                }.getOrNull()
                if (!coverUri.isNullOrBlank()) {
                    dao.updateBookCover(book.id, coverUri)
                }
            }
    }

    override suspend fun saveReadingProgress(progress: ReadingProgress) {
        dao.upsertReadingProgress(progress.copy(updatedAt = System.currentTimeMillis()).toEntity())
        settingsDataStore.setLastBook(progress.bookId)
    }

    override suspend fun saveTtsProgress(progress: TtsProgress) {
        dao.upsertTtsProgress(progress.copy(updatedAt = System.currentTimeMillis()).toEntity())
    }

    override suspend fun addBookmark(bookId: String, position: Int, title: String): Bookmark {
        val bookmark = BookmarkEntity(
            id = UUID.randomUUID().toString(),
            bookId = bookId,
            position = position,
            title = title,
            createdAt = System.currentTimeMillis()
        )
        dao.upsertBookmark(bookmark)
        return bookmark.toDomain()
    }

    override suspend fun deleteBookmark(bookmarkId: String) {
        dao.deleteBookmark(bookmarkId)
    }
}
