package com.astus.reader.core.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class TtsEngineInfo(
    val label: String,
    val packageName: String
)

data class TtsPlaybackState(
    val isReady: Boolean = false,
    val isPlaying: Boolean = false,
    val currentSentenceIndex: Int = 0,
    val speed: Float = 1.0f,
    val pitch: Float = 1.0f,
    val enginePackage: String? = null
)

@Singleton
class TtsController @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var tts: TextToSpeech? = null
    private var sentences: List<String> = emptyList()
    private var currentIndex: Int = 0

    private val _state = MutableStateFlow(TtsPlaybackState())
    val state: StateFlow<TtsPlaybackState> = _state.asStateFlow()

    val engines: List<TtsEngineInfo>
        get() {
            val instance = tts
            return instance?.engines
                ?.map { TtsEngineInfo(label = it.label ?: it.name, packageName = it.name) }
                ?.sortedBy { it.label.lowercase(Locale.getDefault()) }
                ?: emptyList()
        }

    init {
        createEngine(null)
    }

    fun setEngine(packageName: String?) {
        if (_state.value.enginePackage == packageName) return
        tts?.shutdown()
        createEngine(packageName)
    }

    fun setSpeed(speed: Float) {
        val safeSpeed = speed.coerceIn(0.5f, 2.5f)
        tts?.setSpeechRate(safeSpeed)
        _state.value = _state.value.copy(speed = safeSpeed)
        if (_state.value.isPlaying) speakCurrent()
    }

    fun setPitch(pitch: Float) {
        val safePitch = pitch.coerceIn(0.5f, 2.0f)
        tts?.setPitch(safePitch)
        _state.value = _state.value.copy(pitch = safePitch)
        if (_state.value.isPlaying) speakCurrent()
    }

    fun play(sourceSentences: List<String>, startIndex: Int = currentIndex) {
        if (sourceSentences.isEmpty()) return
        sentences = sourceSentences
        currentIndex = startIndex.coerceIn(0, sentences.lastIndex)
        speakCurrent()
    }

    fun pause() {
        tts?.stop()
        _state.value = _state.value.copy(isPlaying = false, currentSentenceIndex = currentIndex)
    }

    fun resume() {
        if (sentences.isNotEmpty()) speakCurrent()
    }

    fun next() {
        if (sentences.isEmpty()) return
        currentIndex = (currentIndex + 1).coerceAtMost(sentences.lastIndex)
        speakCurrent()
    }

    fun previous() {
        if (sentences.isEmpty()) return
        currentIndex = (currentIndex - 1).coerceAtLeast(0)
        speakCurrent()
    }

    fun stop() {
        tts?.stop()
        _state.value = _state.value.copy(isPlaying = false)
    }

    private fun createEngine(packageName: String?) {
        tts = TextToSpeech(context, { status ->
            val ready = status == TextToSpeech.SUCCESS
            tts?.language = Locale.getDefault()
            tts?.setSpeechRate(_state.value.speed)
            tts?.setPitch(_state.value.pitch)
            tts?.setOnUtteranceProgressListener(listener)
            _state.value = _state.value.copy(
                isReady = ready,
                isPlaying = false,
                enginePackage = packageName ?: tts?.defaultEngine
            )
        }, packageName)
    }

    private fun speakCurrent() {
        val sentence = sentences.getOrNull(currentIndex) ?: return
        val utteranceId = "astus-$currentIndex"
        _state.value = _state.value.copy(isPlaying = true, currentSentenceIndex = currentIndex)
        tts?.speak(sentence, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
    }

    private val listener = object : UtteranceProgressListener() {
        override fun onStart(utteranceId: String?) {
            _state.value = _state.value.copy(isPlaying = true, currentSentenceIndex = currentIndex)
        }

        override fun onDone(utteranceId: String?) {
            if (sentences.isEmpty()) {
                _state.value = _state.value.copy(isPlaying = false)
                return
            }
            if (currentIndex < sentences.lastIndex) {
                currentIndex += 1
                speakCurrent()
            } else {
                _state.value = _state.value.copy(isPlaying = false, currentSentenceIndex = currentIndex)
            }
        }

        @Deprecated("Deprecated in Java")
        override fun onError(utteranceId: String?) {
            _state.value = _state.value.copy(isPlaying = false)
        }
    }
}
