package com.astus.reader.feature_reader;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.astus.reader.core.datastore.SettingsDataStore;
import com.astus.reader.core.tts.TtsController;
import com.astus.reader.core.tts.TtsEngineInfo;
import com.astus.reader.core.ui.ReaderThemeMode;
import com.astus.reader.core.util.SentenceRange;
import com.astus.reader.core.util.TextChunker;
import com.astus.reader.domain.model.Book;
import com.astus.reader.domain.model.Bookmark;
import com.astus.reader.domain.model.ReadingProgress;
import com.astus.reader.domain.model.TtsProgress;
import com.astus.reader.domain.repository.BookRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;
import kotlinx.coroutines.flow.StateFlow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0018\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0015H\u0002J\b\u0010\u001f\u001a\u00020\u0015H\u0002J\b\u0010 \u001a\u00020\u0015H\u0002J\b\u0010!\u001a\u00020\u0015H\u0002J\b\u0010\"\u001a\u00020\u0015H\u0002J\u000e\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020%J\u0010\u0010&\u001a\u00020\u00152\u0006\u0010\'\u001a\u00020\u001dH\u0002J\u0010\u0010(\u001a\u00020\u00152\u0006\u0010\'\u001a\u00020\u001dH\u0002J\u001e\u0010)\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\b\u0010+\u001a\u00020\u0015H\u0002J\u0010\u0010,\u001a\u00020\u00152\u0006\u0010-\u001a\u00020.H\u0002J1\u0010/\u001a\u00020\u00152\n\b\u0002\u00100\u001a\u0004\u0018\u0001012\n\b\u0002\u00102\u001a\u0004\u0018\u0001012\n\b\u0002\u00103\u001a\u0004\u0018\u000104H\u0002\u00a2\u0006\u0002\u00105J\u0012\u00106\u001a\u00020\u00152\b\u00107\u001a\u0004\u0018\u00010\u000fH\u0002J\u0010\u00108\u001a\u00020\u00152\u0006\u00109\u001a\u000201H\u0002J\u0010\u0010:\u001a\u00020\u00152\u0006\u0010;\u001a\u000201H\u0002R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006<"}, d2 = {"Lcom/astus/reader/feature_reader/ReaderViewModel;", "Landroidx/lifecycle/ViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "repository", "Lcom/astus/reader/domain/repository/BookRepository;", "settingsDataStore", "Lcom/astus/reader/core/datastore/SettingsDataStore;", "ttsController", "Lcom/astus/reader/core/tts/TtsController;", "(Landroidx/lifecycle/SavedStateHandle;Lcom/astus/reader/domain/repository/BookRepository;Lcom/astus/reader/core/datastore/SettingsDataStore;Lcom/astus/reader/core/tts/TtsController;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/astus/reader/feature_reader/ReaderState;", "bookId", "", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "addBookmark", "", "fallbackSentence", "", "Lcom/astus/reader/core/util/SentenceRange;", "book", "Lcom/astus/reader/domain/model/Book;", "jumpToBookmark", "position", "", "observeBook", "observeBookmarks", "observeProgress", "observeSettings", "observeTts", "onIntent", "intent", "Lcom/astus/reader/feature_reader/ReaderIntent;", "saveVisibleSentence", "index", "selectSentence", "sentenceIndexForPosition", "sentences", "togglePlayback", "updateKeepScreenOn", "enabled", "", "updateReaderStyle", "fontSize", "", "lineHeight", "theme", "Lcom/astus/reader/core/ui/ReaderThemeMode;", "(Ljava/lang/Float;Ljava/lang/Float;Lcom/astus/reader/core/ui/ReaderThemeMode;)V", "updateTtsEngine", "packageName", "updateTtsPitch", "pitch", "updateTtsSpeed", "speed", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ReaderViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.astus.reader.domain.repository.BookRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.astus.reader.core.datastore.SettingsDataStore settingsDataStore = null;
    @org.jetbrains.annotations.NotNull()
    private final com.astus.reader.core.tts.TtsController ttsController = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String bookId = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.astus.reader.feature_reader.ReaderState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.astus.reader.feature_reader.ReaderState> state = null;
    
    @javax.inject.Inject()
    public ReaderViewModel(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull()
    com.astus.reader.domain.repository.BookRepository repository, @org.jetbrains.annotations.NotNull()
    com.astus.reader.core.datastore.SettingsDataStore settingsDataStore, @org.jetbrains.annotations.NotNull()
    com.astus.reader.core.tts.TtsController ttsController) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.astus.reader.feature_reader.ReaderState> getState() {
        return null;
    }
    
    public final void onIntent(@org.jetbrains.annotations.NotNull()
    com.astus.reader.feature_reader.ReaderIntent intent) {
    }
    
    private final void observeBook() {
    }
    
    private final void observeProgress() {
    }
    
    private final void observeBookmarks() {
    }
    
    private final void observeSettings() {
    }
    
    private final void observeTts() {
    }
    
    private final void togglePlayback() {
    }
    
    private final void updateReaderStyle(java.lang.Float fontSize, java.lang.Float lineHeight, com.astus.reader.core.ui.ReaderThemeMode theme) {
    }
    
    private final void updateTtsSpeed(float speed) {
    }
    
    private final void updateTtsPitch(float pitch) {
    }
    
    private final void updateKeepScreenOn(boolean enabled) {
    }
    
    private final void updateTtsEngine(java.lang.String packageName) {
    }
    
    private final void saveVisibleSentence(int index) {
    }
    
    private final void selectSentence(int index) {
    }
    
    private final void addBookmark() {
    }
    
    private final void jumpToBookmark(int position) {
    }
    
    private final int sentenceIndexForPosition(int position, java.util.List<com.astus.reader.core.util.SentenceRange> sentences) {
        return 0;
    }
    
    private final java.util.List<com.astus.reader.core.util.SentenceRange> fallbackSentence(com.astus.reader.domain.model.Book book) {
        return null;
    }
}