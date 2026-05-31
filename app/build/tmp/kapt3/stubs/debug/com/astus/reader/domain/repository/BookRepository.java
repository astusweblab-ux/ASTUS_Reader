package com.astus.reader.domain.repository;

import android.net.Uri;
import com.astus.reader.domain.model.Book;
import com.astus.reader.domain.model.Bookmark;
import com.astus.reader.domain.model.ReadingProgress;
import com.astus.reader.domain.model.TtsProgress;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\rJ\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\rJ\"\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0011H\u00a6@\u00a2\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u00162\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001c\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00110\u00162\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0014\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00110\u0016H&J\u0018\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u00162\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u00162\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001c\u0010\u001d\u001a\u00020\u000b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011H\u00a6@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u001aH\u00a6@\u00a2\u0006\u0002\u0010!J\u0016\u0010\"\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u001cH\u00a6@\u00a2\u0006\u0002\u0010#\u00a8\u0006$"}, d2 = {"Lcom/astus/reader/domain/repository/BookRepository;", "", "addBookmark", "Lcom/astus/reader/domain/model/Bookmark;", "bookId", "", "position", "", "title", "(Ljava/lang/String;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBookmark", "", "bookmarkId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBook", "Lcom/astus/reader/domain/model/Book;", "importBooks", "", "uris", "Landroid/net/Uri;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeBook", "Lkotlinx/coroutines/flow/Flow;", "observeBookmarks", "observeBooks", "observeReadingProgress", "Lcom/astus/reader/domain/model/ReadingProgress;", "observeTtsProgress", "Lcom/astus/reader/domain/model/TtsProgress;", "refreshMissingCovers", "books", "saveReadingProgress", "progress", "(Lcom/astus/reader/domain/model/ReadingProgress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveTtsProgress", "(Lcom/astus/reader/domain/model/TtsProgress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface BookRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.astus.reader.domain.model.Book>> observeBooks();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.astus.reader.domain.model.Book> observeBook(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.astus.reader.domain.model.Bookmark>> observeBookmarks(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.astus.reader.domain.model.ReadingProgress> observeReadingProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.astus.reader.domain.model.TtsProgress> observeTtsProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBook(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.astus.reader.domain.model.Book> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object importBooks(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.net.Uri> uris, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.astus.reader.domain.model.Book>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object refreshMissingCovers(@org.jetbrains.annotations.NotNull()
    java.util.List<com.astus.reader.domain.model.Book> books, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveReadingProgress(@org.jetbrains.annotations.NotNull()
    com.astus.reader.domain.model.ReadingProgress progress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveTtsProgress(@org.jetbrains.annotations.NotNull()
    com.astus.reader.domain.model.TtsProgress progress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addBookmark(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId, int position, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.astus.reader.domain.model.Bookmark> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteBookmark(@org.jetbrains.annotations.NotNull()
    java.lang.String bookmarkId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}