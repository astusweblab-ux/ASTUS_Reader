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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0002J\u000e\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010\u0019\u001a\u00020\u00122\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0014H\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001c"}, d2 = {"Lcom/astus/reader/feature_library/LibraryViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/astus/reader/domain/repository/BookRepository;", "settingsDataStore", "Lcom/astus/reader/core/datastore/SettingsDataStore;", "(Lcom/astus/reader/domain/repository/BookRepository;Lcom/astus/reader/core/datastore/SettingsDataStore;)V", "coverRefreshAttempts", "", "", "localState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/astus/reader/feature_library/LibraryState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "importBooks", "", "uris", "", "Landroid/net/Uri;", "onIntent", "intent", "Lcom/astus/reader/feature_library/LibraryIntent;", "refreshMissingCoversOnce", "books", "Lcom/astus/reader/domain/model/Book;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class LibraryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.astus.reader.domain.repository.BookRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.astus.reader.feature_library.LibraryState> localState = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.String> coverRefreshAttempts = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.astus.reader.feature_library.LibraryState> state = null;
    
    @javax.inject.Inject()
    public LibraryViewModel(@org.jetbrains.annotations.NotNull()
    com.astus.reader.domain.repository.BookRepository repository, @org.jetbrains.annotations.NotNull()
    com.astus.reader.core.datastore.SettingsDataStore settingsDataStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.astus.reader.feature_library.LibraryState> getState() {
        return null;
    }
    
    public final void onIntent(@org.jetbrains.annotations.NotNull()
    com.astus.reader.feature_library.LibraryIntent intent) {
    }
    
    private final void importBooks(java.util.List<? extends android.net.Uri> uris) {
    }
    
    private final void refreshMissingCoversOnce(java.util.List<com.astus.reader.domain.model.Book> books) {
    }
}