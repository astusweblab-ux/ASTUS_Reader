package com.astus.reader.feature_library;

import android.net.Uri;
import androidx.lifecycle.ViewModel;
import com.astus.reader.core.datastore.SettingsDataStore;
import com.astus.reader.domain.model.Book;
import com.astus.reader.domain.repository.BookRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BA\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u000bJ\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0006H\u00c6\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\tH\u00c6\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003JE\u0010\u001d\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001J\u0013\u0010\u001e\u001a\u00020\t2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010 \u001a\u00020!H\u00d6\u0001J\t\u0010\"\u001a\u00020\u0006H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\rR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0015R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012\u00a8\u0006#"}, d2 = {"Lcom/astus/reader/feature_library/LibraryState;", "", "books", "", "Lcom/astus/reader/domain/model/Book;", "query", "", "lastBookId", "isImporting", "", "error", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getBooks", "()Ljava/util/List;", "continueBook", "getContinueBook", "()Lcom/astus/reader/domain/model/Book;", "getError", "()Ljava/lang/String;", "filteredBooks", "getFilteredBooks", "()Z", "getLastBookId", "getQuery", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class LibraryState {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.astus.reader.domain.model.Book> books = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String query = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String lastBookId = null;
    private final boolean isImporting = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    
    public LibraryState(@org.jetbrains.annotations.NotNull()
    java.util.List<com.astus.reader.domain.model.Book> books, @org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.Nullable()
    java.lang.String lastBookId, boolean isImporting, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.astus.reader.domain.model.Book> getBooks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastBookId() {
        return null;
    }
    
    public final boolean isImporting() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.astus.reader.domain.model.Book> getFilteredBooks() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.astus.reader.domain.model.Book getContinueBook() {
        return null;
    }
    
    public LibraryState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.astus.reader.domain.model.Book> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    public final boolean component4() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.astus.reader.feature_library.LibraryState copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.astus.reader.domain.model.Book> books, @org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.Nullable()
    java.lang.String lastBookId, boolean isImporting, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}