package com.astus.reader.data;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.astus.reader.core.database.dao.BookDao;
import com.astus.reader.core.database.entity.BookmarkEntity;
import com.astus.reader.core.datastore.SettingsDataStore;
import com.astus.reader.core.util.BookParser;
import com.astus.reader.core.util.UnsupportedBookFormatException;
import com.astus.reader.domain.model.Book;
import com.astus.reader.domain.model.Bookmark;
import com.astus.reader.domain.model.ReadingProgress;
import com.astus.reader.domain.model.TtsProgress;
import com.astus.reader.domain.repository.BookRepository;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ&\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\u0014J\"\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0018H\u0096@\u00a2\u0006\u0002\u0010\u001bJ\u0018\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u001d2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00180\u001d2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0014\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00180\u001dH\u0016J\u0018\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!0\u001d2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#0\u001d2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010$\u001a\u00020\u00122\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00160\u0018H\u0096@\u00a2\u0006\u0002\u0010\u001bJ\u0016\u0010&\u001a\u00020\u00122\u0006\u0010\'\u001a\u00020!H\u0096@\u00a2\u0006\u0002\u0010(J\u0016\u0010)\u001a\u00020\u00122\u0006\u0010\'\u001a\u00020#H\u0096@\u00a2\u0006\u0002\u0010*R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/astus/reader/data/BookRepositoryImpl;", "Lcom/astus/reader/domain/repository/BookRepository;", "context", "Landroid/content/Context;", "dao", "Lcom/astus/reader/core/database/dao/BookDao;", "settingsDataStore", "Lcom/astus/reader/core/datastore/SettingsDataStore;", "(Landroid/content/Context;Lcom/astus/reader/core/database/dao/BookDao;Lcom/astus/reader/core/datastore/SettingsDataStore;)V", "addBookmark", "Lcom/astus/reader/domain/model/Bookmark;", "bookId", "", "position", "", "title", "(Ljava/lang/String;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBookmark", "", "bookmarkId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBook", "Lcom/astus/reader/domain/model/Book;", "importBooks", "", "uris", "Landroid/net/Uri;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeBook", "Lkotlinx/coroutines/flow/Flow;", "observeBookmarks", "observeBooks", "observeReadingProgress", "Lcom/astus/reader/domain/model/ReadingProgress;", "observeTtsProgress", "Lcom/astus/reader/domain/model/TtsProgress;", "refreshMissingCovers", "books", "saveReadingProgress", "progress", "(Lcom/astus/reader/domain/model/ReadingProgress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveTtsProgress", "(Lcom/astus/reader/domain/model/TtsProgress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class BookRepositoryImpl implements com.astus.reader.domain.repository.BookRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.astus.reader.core.database.dao.BookDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.astus.reader.core.datastore.SettingsDataStore settingsDataStore = null;
    
    @javax.inject.Inject()
    public BookRepositoryImpl(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.astus.reader.core.database.dao.BookDao dao, @org.jetbrains.annotations.NotNull()
    com.astus.reader.core.datastore.SettingsDataStore settingsDataStore) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.astus.reader.domain.model.Book>> observeBooks() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.astus.reader.domain.model.Book> observeBook(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.astus.reader.domain.model.Bookmark>> observeBookmarks(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.astus.reader.domain.model.ReadingProgress> observeReadingProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.astus.reader.domain.model.TtsProgress> observeTtsProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getBook(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.astus.reader.domain.model.Book> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object importBooks(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.net.Uri> uris, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.astus.reader.domain.model.Book>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object refreshMissingCovers(@org.jetbrains.annotations.NotNull()
    java.util.List<com.astus.reader.domain.model.Book> books, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveReadingProgress(@org.jetbrains.annotations.NotNull()
    com.astus.reader.domain.model.ReadingProgress progress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveTtsProgress(@org.jetbrains.annotations.NotNull()
    com.astus.reader.domain.model.TtsProgress progress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addBookmark(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId, int position, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.astus.reader.domain.model.Bookmark> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteBookmark(@org.jetbrains.annotations.NotNull()
    java.lang.String bookmarkId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}