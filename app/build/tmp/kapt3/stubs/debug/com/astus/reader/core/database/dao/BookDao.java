package com.astus.reader.core.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Upsert;
import com.astus.reader.core.database.entity.BookEntity;
import com.astus.reader.core.database.entity.BookmarkEntity;
import com.astus.reader.core.database.entity.ReadingProgressEntity;
import com.astus.reader.core.database.entity.TtsProgressEntity;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\f\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\f\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00122\u0006\u0010\f\u001a\u00020\tH\'J\u001c\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u00122\u0006\u0010\f\u001a\u00020\tH\'J\u0014\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00140\u0012H\'J\u0018\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00122\u0006\u0010\f\u001a\u00020\tH\'J\u0018\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u00122\u0006\u0010\f\u001a\u00020\tH\'J\u001e\u0010\u0019\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u001bJ\u0016\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0015H\u00a7@\u00a2\u0006\u0002\u0010\u001eJ\u001c\u0010\u001f\u001a\u00020\u00032\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00050\u0014H\u00a7@\u00a2\u0006\u0002\u0010!J\u0016\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010$J\u0016\u0010%\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010&\u00a8\u0006\'"}, d2 = {"Lcom/astus/reader/core/database/dao/BookDao;", "", "deleteBook", "", "book", "Lcom/astus/reader/core/database/entity/BookEntity;", "(Lcom/astus/reader/core/database/entity/BookEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBookmark", "bookmarkId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBook", "bookId", "getReadingProgress", "Lcom/astus/reader/core/database/entity/ReadingProgressEntity;", "getTtsProgress", "Lcom/astus/reader/core/database/entity/TtsProgressEntity;", "observeBook", "Lkotlinx/coroutines/flow/Flow;", "observeBookmarks", "", "Lcom/astus/reader/core/database/entity/BookmarkEntity;", "observeBooks", "observeReadingProgress", "observeTtsProgress", "updateBookCover", "coverUri", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertBookmark", "bookmark", "(Lcom/astus/reader/core/database/entity/BookmarkEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertBooks", "books", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertReadingProgress", "progress", "(Lcom/astus/reader/core/database/entity/ReadingProgressEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertTtsProgress", "(Lcom/astus/reader/core/database/entity/TtsProgressEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface BookDao {
    
    @androidx.room.Query(value = "SELECT * FROM books ORDER BY importedAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.astus.reader.core.database.entity.BookEntity>> observeBooks();
    
    @androidx.room.Query(value = "SELECT * FROM books WHERE id = :bookId")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.astus.reader.core.database.entity.BookEntity> observeBook(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId);
    
    @androidx.room.Query(value = "SELECT * FROM books WHERE id = :bookId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBook(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.astus.reader.core.database.entity.BookEntity> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertBooks(@org.jetbrains.annotations.NotNull()
    java.util.List<com.astus.reader.core.database.entity.BookEntity> books, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE books SET coverUri = :coverUri WHERE id = :bookId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateBookCover(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId, @org.jetbrains.annotations.NotNull()
    java.lang.String coverUri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteBook(@org.jetbrains.annotations.NotNull()
    com.astus.reader.core.database.entity.BookEntity book, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM bookmarks WHERE bookId = :bookId ORDER BY position ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.astus.reader.core.database.entity.BookmarkEntity>> observeBookmarks(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertBookmark(@org.jetbrains.annotations.NotNull()
    com.astus.reader.core.database.entity.BookmarkEntity bookmark, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM bookmarks WHERE id = :bookmarkId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteBookmark(@org.jetbrains.annotations.NotNull()
    java.lang.String bookmarkId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM reading_progress WHERE bookId = :bookId")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.astus.reader.core.database.entity.ReadingProgressEntity> observeReadingProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId);
    
    @androidx.room.Query(value = "SELECT * FROM reading_progress WHERE bookId = :bookId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getReadingProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.astus.reader.core.database.entity.ReadingProgressEntity> $completion);
    
    @androidx.room.Upsert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertReadingProgress(@org.jetbrains.annotations.NotNull()
    com.astus.reader.core.database.entity.ReadingProgressEntity progress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM tts_progress WHERE bookId = :bookId")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.astus.reader.core.database.entity.TtsProgressEntity> observeTtsProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId);
    
    @androidx.room.Query(value = "SELECT * FROM tts_progress WHERE bookId = :bookId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTtsProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.astus.reader.core.database.entity.TtsProgressEntity> $completion);
    
    @androidx.room.Upsert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertTtsProgress(@org.jetbrains.annotations.NotNull()
    com.astus.reader.core.database.entity.TtsProgressEntity progress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}