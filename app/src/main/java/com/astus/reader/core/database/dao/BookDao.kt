package com.astus.reader.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.astus.reader.core.database.entity.BookEntity
import com.astus.reader.core.database.entity.BookmarkEntity
import com.astus.reader.core.database.entity.ReadingProgressEntity
import com.astus.reader.core.database.entity.TtsProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM books ORDER BY importedAt DESC")
    fun observeBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE id = :bookId")
    fun observeBook(bookId: String): Flow<BookEntity?>

    @Query("SELECT * FROM books WHERE id = :bookId")
    suspend fun getBook(bookId: String): BookEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertBooks(books: List<BookEntity>)

    @Query("UPDATE books SET coverUri = :coverUri WHERE id = :bookId")
    suspend fun updateBookCover(bookId: String, coverUri: String)

    @Delete
    suspend fun deleteBook(book: BookEntity)

    @Query("SELECT * FROM bookmarks WHERE bookId = :bookId ORDER BY position ASC")
    fun observeBookmarks(bookId: String): Flow<List<BookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertBookmark(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmarks WHERE id = :bookmarkId")
    suspend fun deleteBookmark(bookmarkId: String)

    @Query("SELECT * FROM reading_progress WHERE bookId = :bookId")
    fun observeReadingProgress(bookId: String): Flow<ReadingProgressEntity?>

    @Query("SELECT * FROM reading_progress WHERE bookId = :bookId")
    suspend fun getReadingProgress(bookId: String): ReadingProgressEntity?

    @Upsert
    suspend fun upsertReadingProgress(progress: ReadingProgressEntity)

    @Query("SELECT * FROM tts_progress WHERE bookId = :bookId")
    fun observeTtsProgress(bookId: String): Flow<TtsProgressEntity?>

    @Query("SELECT * FROM tts_progress WHERE bookId = :bookId")
    suspend fun getTtsProgress(bookId: String): TtsProgressEntity?

    @Upsert
    suspend fun upsertTtsProgress(progress: TtsProgressEntity)
}
