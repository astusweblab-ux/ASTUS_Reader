package com.astus.reader.domain.repository

import android.net.Uri
import com.astus.reader.domain.model.Book
import com.astus.reader.domain.model.Bookmark
import com.astus.reader.domain.model.ReadingProgress
import com.astus.reader.domain.model.TtsProgress
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun observeBooks(): Flow<List<Book>>
    fun observeBook(bookId: String): Flow<Book?>
    fun observeBookmarks(bookId: String): Flow<List<Bookmark>>
    fun observeReadingProgress(bookId: String): Flow<ReadingProgress?>
    fun observeTtsProgress(bookId: String): Flow<TtsProgress?>
    suspend fun getBook(bookId: String): Book?
    suspend fun importBooks(uris: List<Uri>): List<Book>
    suspend fun refreshMissingCovers(books: List<Book>)
    suspend fun saveReadingProgress(progress: ReadingProgress)
    suspend fun saveTtsProgress(progress: TtsProgress)
    suspend fun addBookmark(bookId: String, position: Int, title: String): Bookmark
    suspend fun deleteBookmark(bookmarkId: String)
}
