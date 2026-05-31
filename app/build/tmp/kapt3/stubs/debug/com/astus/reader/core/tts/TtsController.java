package com.astus.reader.core.tts;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlinx.coroutines.flow.StateFlow;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0012H\u0002J\u0006\u0010\u001c\u001a\u00020\u001aJ\u0006\u0010\u001d\u001a\u00020\u001aJ\u001e\u0010\u001e\u001a\u00020\u001a2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00120\u000b2\b\b\u0002\u0010 \u001a\u00020\tJ\u0006\u0010!\u001a\u00020\u001aJ\u0006\u0010\"\u001a\u00020\u001aJ\u0010\u0010#\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0012J\u000e\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020&J\u000e\u0010\'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020&J\b\u0010)\u001a\u00020\u001aH\u0002J\u0006\u0010*\u001a\u00020\u001aR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/astus/reader/core/tts/TtsController;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/astus/reader/core/tts/TtsPlaybackState;", "currentIndex", "", "engines", "", "Lcom/astus/reader/core/tts/TtsEngineInfo;", "getEngines", "()Ljava/util/List;", "listener", "Landroid/speech/tts/UtteranceProgressListener;", "sentences", "", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "tts", "Landroid/speech/tts/TextToSpeech;", "createEngine", "", "packageName", "next", "pause", "play", "sourceSentences", "startIndex", "previous", "resume", "setEngine", "setPitch", "pitch", "", "setSpeed", "speed", "speakCurrent", "stop", "app_debug"})
public final class TtsController {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.Nullable()
    private android.speech.tts.TextToSpeech tts;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<java.lang.String> sentences;
    private int currentIndex = 0;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.astus.reader.core.tts.TtsPlaybackState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.astus.reader.core.tts.TtsPlaybackState> state = null;
    @org.jetbrains.annotations.NotNull()
    private final android.speech.tts.UtteranceProgressListener listener = null;
    
    @javax.inject.Inject()
    public TtsController(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.astus.reader.core.tts.TtsPlaybackState> getState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.astus.reader.core.tts.TtsEngineInfo> getEngines() {
        return null;
    }
    
    public final void setEngine(@org.jetbrains.annotations.Nullable()
    java.lang.String packageName) {
    }
    
    public final void setSpeed(float speed) {
    }
    
    public final void setPitch(float pitch) {
    }
    
    public final void play(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> sourceSentences, int startIndex) {
    }
    
    public final void pause() {
    }
    
    public final void resume() {
    }
    
    public final void next() {
    }
    
    public final void previous() {
    }
    
    public final void stop() {
    }
    
    private final void createEngine(java.lang.String packageName) {
    }
    
    private final void speakCurrent() {
    }
}