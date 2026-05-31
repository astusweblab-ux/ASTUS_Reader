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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b/\b\u0087\b\u0018\u00002\u00020\u0001B\u00bb\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0014\u0012\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0007\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u001cJ\t\u00104\u001a\u00020\u0003H\u00c6\u0003J\t\u00105\u001a\u00020\u0014H\u00c6\u0003J\t\u00106\u001a\u00020\u0014H\u00c6\u0003J\t\u00107\u001a\u00020\u000fH\u00c6\u0003J\t\u00108\u001a\u00020\u000fH\u00c6\u0003J\t\u00109\u001a\u00020\u0014H\u00c6\u0003J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0007H\u00c6\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000f\u0010=\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\u000f\u0010>\u001a\b\u0012\u0004\u0012\u00020\n0\u0007H\u00c6\u0003J\t\u0010?\u001a\u00020\fH\u00c6\u0003J\t\u0010@\u001a\u00020\fH\u00c6\u0003J\t\u0010A\u001a\u00020\u000fH\u00c6\u0003J\t\u0010B\u001a\u00020\u000fH\u00c6\u0003J\t\u0010C\u001a\u00020\u0012H\u00c6\u0003J\u00bf\u0001\u0010D\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u000f2\b\b\u0002\u0010\u0018\u001a\u00020\u00142\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00072\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010E\u001a\u00020\u00142\b\u0010F\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010G\u001a\u00020\fH\u00d6\u0001J\t\u0010H\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010 R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\"R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u0011\u0010\r\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010$R\u0011\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010*R\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010*R\u0011\u0010\u0018\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010*R\u0011\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010(R\u0011\u0010-\u001a\u00020\u000f8F\u00a2\u0006\u0006\u001a\u0004\b.\u0010(R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\"R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\u0017\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010(R\u0011\u0010\u0016\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010(\u00a8\u0006I"}, d2 = {"Lcom/astus/reader/feature_reader/ReaderState;", "", "bookId", "", "book", "Lcom/astus/reader/domain/model/Book;", "sentences", "", "Lcom/astus/reader/core/util/SentenceRange;", "bookmarks", "Lcom/astus/reader/domain/model/Bookmark;", "currentSentenceIndex", "", "initialSentenceIndex", "fontSizeSp", "", "lineHeightMultiplier", "themeMode", "Lcom/astus/reader/core/ui/ReaderThemeMode;", "isTtsReady", "", "isPlaying", "ttsSpeed", "ttsPitch", "keepScreenOn", "engines", "Lcom/astus/reader/core/tts/TtsEngineInfo;", "enginePackage", "(Ljava/lang/String;Lcom/astus/reader/domain/model/Book;Ljava/util/List;Ljava/util/List;IIFFLcom/astus/reader/core/ui/ReaderThemeMode;ZZFFZLjava/util/List;Ljava/lang/String;)V", "getBook", "()Lcom/astus/reader/domain/model/Book;", "getBookId", "()Ljava/lang/String;", "getBookmarks", "()Ljava/util/List;", "getCurrentSentenceIndex", "()I", "getEnginePackage", "getEngines", "getFontSizeSp", "()F", "getInitialSentenceIndex", "()Z", "getKeepScreenOn", "getLineHeightMultiplier", "readingProgress", "getReadingProgress", "getSentences", "getThemeMode", "()Lcom/astus/reader/core/ui/ReaderThemeMode;", "getTtsPitch", "getTtsSpeed", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class ReaderState {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String bookId = null;
    @org.jetbrains.annotations.Nullable()
    private final com.astus.reader.domain.model.Book book = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.astus.reader.core.util.SentenceRange> sentences = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.astus.reader.domain.model.Bookmark> bookmarks = null;
    private final int currentSentenceIndex = 0;
    private final int initialSentenceIndex = 0;
    private final float fontSizeSp = 0.0F;
    private final float lineHeightMultiplier = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final com.astus.reader.core.ui.ReaderThemeMode themeMode = null;
    private final boolean isTtsReady = false;
    private final boolean isPlaying = false;
    private final float ttsSpeed = 0.0F;
    private final float ttsPitch = 0.0F;
    private final boolean keepScreenOn = false;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.astus.reader.core.tts.TtsEngineInfo> engines = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String enginePackage = null;
    
    public ReaderState(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId, @org.jetbrains.annotations.Nullable()
    com.astus.reader.domain.model.Book book, @org.jetbrains.annotations.NotNull()
    java.util.List<com.astus.reader.core.util.SentenceRange> sentences, @org.jetbrains.annotations.NotNull()
    java.util.List<com.astus.reader.domain.model.Bookmark> bookmarks, int currentSentenceIndex, int initialSentenceIndex, float fontSizeSp, float lineHeightMultiplier, @org.jetbrains.annotations.NotNull()
    com.astus.reader.core.ui.ReaderThemeMode themeMode, boolean isTtsReady, boolean isPlaying, float ttsSpeed, float ttsPitch, boolean keepScreenOn, @org.jetbrains.annotations.NotNull()
    java.util.List<com.astus.reader.core.tts.TtsEngineInfo> engines, @org.jetbrains.annotations.Nullable()
    java.lang.String enginePackage) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBookId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.astus.reader.domain.model.Book getBook() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.astus.reader.core.util.SentenceRange> getSentences() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.astus.reader.domain.model.Bookmark> getBookmarks() {
        return null;
    }
    
    public final int getCurrentSentenceIndex() {
        return 0;
    }
    
    public final int getInitialSentenceIndex() {
        return 0;
    }
    
    public final float getFontSizeSp() {
        return 0.0F;
    }
    
    public final float getLineHeightMultiplier() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.astus.reader.core.ui.ReaderThemeMode getThemeMode() {
        return null;
    }
    
    public final boolean isTtsReady() {
        return false;
    }
    
    public final boolean isPlaying() {
        return false;
    }
    
    public final float getTtsSpeed() {
        return 0.0F;
    }
    
    public final float getTtsPitch() {
        return 0.0F;
    }
    
    public final boolean getKeepScreenOn() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.astus.reader.core.tts.TtsEngineInfo> getEngines() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getEnginePackage() {
        return null;
    }
    
    public final float getReadingProgress() {
        return 0.0F;
    }
    
    public ReaderState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    public final boolean component10() {
        return false;
    }
    
    public final boolean component11() {
        return false;
    }
    
    public final float component12() {
        return 0.0F;
    }
    
    public final float component13() {
        return 0.0F;
    }
    
    public final boolean component14() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.astus.reader.core.tts.TtsEngineInfo> component15() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.astus.reader.domain.model.Book component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.astus.reader.core.util.SentenceRange> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.astus.reader.domain.model.Bookmark> component4() {
        return null;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final float component7() {
        return 0.0F;
    }
    
    public final float component8() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.astus.reader.core.ui.ReaderThemeMode component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.astus.reader.feature_reader.ReaderState copy(@org.jetbrains.annotations.NotNull()
    java.lang.String bookId, @org.jetbrains.annotations.Nullable()
    com.astus.reader.domain.model.Book book, @org.jetbrains.annotations.NotNull()
    java.util.List<com.astus.reader.core.util.SentenceRange> sentences, @org.jetbrains.annotations.NotNull()
    java.util.List<com.astus.reader.domain.model.Bookmark> bookmarks, int currentSentenceIndex, int initialSentenceIndex, float fontSizeSp, float lineHeightMultiplier, @org.jetbrains.annotations.NotNull()
    com.astus.reader.core.ui.ReaderThemeMode themeMode, boolean isTtsReady, boolean isPlaying, float ttsSpeed, float ttsPitch, boolean keepScreenOn, @org.jetbrains.annotations.NotNull()
    java.util.List<com.astus.reader.core.tts.TtsEngineInfo> engines, @org.jetbrains.annotations.Nullable()
    java.lang.String enginePackage) {
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