package com.astus.reader.feature_reader

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astus.reader.core.datastore.SettingsDataStore
import com.astus.reader.core.tts.TtsController
import com.astus.reader.core.tts.TtsEngineInfo
import com.astus.reader.core.ui.ReaderThemeMode
import com.astus.reader.core.util.SentenceRange
import com.astus.reader.core.util.TextChunker
import com.astus.reader.domain.model.Book
import com.astus.reader.domain.model.Bookmark
import com.astus.reader.domain.model.ReadingProgress
import com.astus.reader.domain.model.TtsProgress
import com.astus.reader.domain.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ReaderState(
    val bookId: String = "",
    val book: Book? = null,
    val sentences: List<SentenceRange> = emptyList(),
    val bookmarks: List<Bookmark> = emptyList(),
    val currentSentenceIndex: Int = 0,
    val initialSentenceIndex: Int = 0,
    val fontSizeSp: Float = 18f,
    val lineHeightMultiplier: Float = 1.55f,
    val themeMode: ReaderThemeMode = ReaderThemeMode.Light,
    val isTtsReady: Boolean = false,
    val isPlaying: Boolean = false,
    val ttsSpeed: Float = 1.0f,
    val ttsPitch: Float = 1.0f,
    val keepScreenOn: Boolean = true,
    val engines: List<TtsEngineInfo> = emptyList(),
    val enginePackage: String? = null
) {
    val readingProgress: Float
        get() = if (sentences.isEmpty()) 0f else (currentSentenceIndex + 1).toFloat() / sentences.size.toFloat()
}

sealed interface ReaderIntent {
    data object PlayPause : ReaderIntent
    data object PreviousSentence : ReaderIntent
    data object NextSentence : ReaderIntent
    data class FontSizeChanged(val value: Float) : ReaderIntent
    data class LineHeightChanged(val value: Float) : ReaderIntent
    data class ThemeChanged(val mode: ReaderThemeMode) : ReaderIntent
    data class SpeedChanged(val value: Float) : ReaderIntent
    data class PitchChanged(val value: Float) : ReaderIntent
    data class KeepScreenOnChanged(val enabled: Boolean) : ReaderIntent
    data class EngineChanged(val packageName: String?) : ReaderIntent
    data class VisibleSentenceChanged(val index: Int) : ReaderIntent
    data class SelectSentence(val index: Int) : ReaderIntent
    data object AddBookmark : ReaderIntent
    data class DeleteBookmark(val id: String) : ReaderIntent
    data class JumpToBookmark(val position: Int) : ReaderIntent
}

@HiltViewModel
class ReaderViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: BookRepository,
    private val settingsDataStore: SettingsDataStore,
    private val ttsController: TtsController
) : ViewModel() {
    private val bookId: String = checkNotNull(savedStateHandle["bookId"])
    private val _state = MutableStateFlow(ReaderState(bookId = bookId))
    val state: StateFlow<ReaderState> = _state.asStateFlow()

    init {
        observeBook()
        observeProgress()
        observeSettings()
        observeBookmarks()
        observeTts()
    }

    fun onIntent(intent: ReaderIntent) {
        when (intent) {
            ReaderIntent.PlayPause -> togglePlayback()
            ReaderIntent.PreviousSentence -> ttsController.previous()
            ReaderIntent.NextSentence -> ttsController.next()
            is ReaderIntent.FontSizeChanged -> updateReaderStyle(fontSize = intent.value)
            is ReaderIntent.LineHeightChanged -> updateReaderStyle(lineHeight = intent.value)
            is ReaderIntent.ThemeChanged -> updateReaderStyle(theme = intent.mode)
            is ReaderIntent.SpeedChanged -> updateTtsSpeed(intent.value)
            is ReaderIntent.PitchChanged -> updateTtsPitch(intent.value)
            is ReaderIntent.KeepScreenOnChanged -> updateKeepScreenOn(intent.enabled)
            is ReaderIntent.EngineChanged -> updateTtsEngine(intent.packageName)
            is ReaderIntent.VisibleSentenceChanged -> saveVisibleSentence(intent.index)
            is ReaderIntent.SelectSentence -> selectSentence(intent.index)
            ReaderIntent.AddBookmark -> addBookmark()
            is ReaderIntent.DeleteBookmark -> viewModelScope.launch { repository.deleteBookmark(intent.id) }
            is ReaderIntent.JumpToBookmark -> jumpToBookmark(intent.position)
        }
    }

    private fun observeBook() {
        viewModelScope.launch {
            repository.observeBook(bookId).collectLatest { book ->
                val sentences = book?.content?.let(TextChunker::sentences).orEmpty()
                _state.update { it.copy(book = book, sentences = sentences.ifEmpty { fallbackSentence(book) }) }
            }
        }
    }

    private fun observeProgress() {
        viewModelScope.launch {
            repository.observeReadingProgress(bookId).collectLatest { progress ->
                if (progress != null) {
                    _state.update {
                        it.copy(
                            initialSentenceIndex = sentenceIndexForPosition(progress.position, it.sentences),
                            fontSizeSp = progress.fontSizeSp,
                            lineHeightMultiplier = progress.lineHeightMultiplier,
                            themeMode = progress.themeMode
                        )
                    }
                }
            }
        }
    }

    private fun observeBookmarks() {
        viewModelScope.launch {
            repository.observeBookmarks(bookId).collectLatest { bookmarks ->
                _state.update { it.copy(bookmarks = bookmarks) }
            }
        }
    }

    private fun observeSettings() {
        viewModelScope.launch {
            settingsDataStore.settings.collectLatest { settings ->
                if (_state.value.ttsSpeed != settings.ttsSpeed) ttsController.setSpeed(settings.ttsSpeed)
                if (_state.value.ttsPitch != settings.ttsPitch) ttsController.setPitch(settings.ttsPitch)
                if (_state.value.enginePackage != settings.ttsEnginePackage && settings.ttsEnginePackage != null) {
                    ttsController.setEngine(settings.ttsEnginePackage)
                }
                _state.update { it.copy(keepScreenOn = settings.keepScreenOn) }
            }
        }
        viewModelScope.launch {
            repository.observeTtsProgress(bookId)
                .map { it?.sentenceIndex ?: 0 }
                .distinctUntilChanged()
                .collectLatest { index ->
                    _state.update { it.copy(currentSentenceIndex = index, initialSentenceIndex = index) }
                }
        }
    }

    private fun observeTts() {
        viewModelScope.launch {
            ttsController.state.collectLatest { ttsState ->
                _state.update {
                    it.copy(
                        isTtsReady = ttsState.isReady,
                        isPlaying = ttsState.isPlaying,
                        currentSentenceIndex = ttsState.currentSentenceIndex,
                        ttsSpeed = ttsState.speed,
                        ttsPitch = ttsState.pitch,
                        enginePackage = ttsState.enginePackage,
                        engines = ttsController.engines
                    )
                }
                repository.saveTtsProgress(
                    TtsProgress(
                        bookId = bookId,
                        sentenceIndex = ttsState.currentSentenceIndex,
                        charPosition = _state.value.sentences.getOrNull(ttsState.currentSentenceIndex)?.start ?: 0,
                        speed = ttsState.speed,
                        pitch = ttsState.pitch,
                        enginePackage = ttsState.enginePackage
                    )
                )
            }
        }
    }

    private fun togglePlayback() {
        val current = _state.value
        if (current.isPlaying) {
            ttsController.pause()
        } else {
            val startIndex = current.currentSentenceIndex.coerceAtLeast(current.initialSentenceIndex)
            ttsController.play(current.sentences.map { it.text }, startIndex)
        }
    }

    private fun updateReaderStyle(
        fontSize: Float? = null,
        lineHeight: Float? = null,
        theme: ReaderThemeMode? = null
    ) {
        val next = _state.value.copy(
            fontSizeSp = fontSize ?: _state.value.fontSizeSp,
            lineHeightMultiplier = lineHeight ?: _state.value.lineHeightMultiplier,
            themeMode = theme ?: _state.value.themeMode
        )
        _state.value = next
        saveVisibleSentence(next.currentSentenceIndex)
    }

    private fun updateTtsSpeed(speed: Float) {
        ttsController.setSpeed(speed)
        viewModelScope.launch {
            settingsDataStore.setTtsSpeed(speed)
            repository.saveTtsProgress(
                TtsProgress(
                    bookId = bookId,
                    sentenceIndex = _state.value.currentSentenceIndex,
                    speed = speed,
                    pitch = _state.value.ttsPitch,
                    enginePackage = _state.value.enginePackage
                )
            )
        }
    }

    private fun updateTtsPitch(pitch: Float) {
        ttsController.setPitch(pitch)
        viewModelScope.launch {
            settingsDataStore.setTtsPitch(pitch)
            repository.saveTtsProgress(
                TtsProgress(
                    bookId = bookId,
                    sentenceIndex = _state.value.currentSentenceIndex,
                    speed = _state.value.ttsSpeed,
                    pitch = pitch,
                    enginePackage = _state.value.enginePackage
                )
            )
        }
    }

    private fun updateKeepScreenOn(enabled: Boolean) {
        _state.update { it.copy(keepScreenOn = enabled) }
        viewModelScope.launch {
            settingsDataStore.setKeepScreenOn(enabled)
        }
    }

    private fun updateTtsEngine(packageName: String?) {
        ttsController.setEngine(packageName)
        viewModelScope.launch {
            settingsDataStore.setTtsEngine(packageName)
            repository.saveTtsProgress(
                TtsProgress(
                    bookId = bookId,
                    sentenceIndex = _state.value.currentSentenceIndex,
                    speed = _state.value.ttsSpeed,
                    pitch = _state.value.ttsPitch,
                    enginePackage = packageName
                )
            )
        }
    }

    private fun saveVisibleSentence(index: Int) {
        val current = _state.value
        val sentence = current.sentences.getOrNull(index) ?: return
        _state.update { it.copy(currentSentenceIndex = index) }
        viewModelScope.launch {
            repository.saveReadingProgress(
                ReadingProgress(
                    bookId = bookId,
                    position = sentence.start,
                    fontSizeSp = current.fontSizeSp,
                    lineHeightMultiplier = current.lineHeightMultiplier,
                    themeMode = current.themeMode
                )
            )
        }
    }

    private fun selectSentence(index: Int) {
        val current = _state.value
        val safeIndex = index.coerceIn(0, (current.sentences.size - 1).coerceAtLeast(0))
        val sentence = current.sentences.getOrNull(safeIndex) ?: return
        _state.update { it.copy(currentSentenceIndex = safeIndex, initialSentenceIndex = safeIndex) }
        if (current.isPlaying) {
            ttsController.play(current.sentences.map { it.text }, safeIndex)
        }
        viewModelScope.launch {
            repository.saveReadingProgress(
                ReadingProgress(
                    bookId = bookId,
                    position = sentence.start,
                    fontSizeSp = current.fontSizeSp,
                    lineHeightMultiplier = current.lineHeightMultiplier,
                    themeMode = current.themeMode
                )
            )
            repository.saveTtsProgress(
                TtsProgress(
                    bookId = bookId,
                    sentenceIndex = safeIndex,
                    charPosition = sentence.start,
                    speed = current.ttsSpeed,
                    pitch = current.ttsPitch,
                    enginePackage = current.enginePackage
                )
            )
        }
    }

    private fun addBookmark() {
        val current = _state.value
        val sentence = current.sentences.getOrNull(current.currentSentenceIndex) ?: return
        viewModelScope.launch {
            repository.addBookmark(
                bookId = bookId,
                position = sentence.start,
                title = TextChunker.previewAt(current.book?.content.orEmpty(), sentence.start)
            )
        }
    }

    private fun jumpToBookmark(position: Int) {
        val index = sentenceIndexForPosition(position, _state.value.sentences)
        saveVisibleSentence(index)
    }

    private fun sentenceIndexForPosition(position: Int, sentences: List<SentenceRange>): Int =
        sentences.indexOfLast { it.start <= position }.coerceAtLeast(0)

    private fun fallbackSentence(book: Book?): List<SentenceRange> {
        val content = book?.content.orEmpty()
        return if (content.isBlank()) emptyList() else listOf(SentenceRange(0, 0, content.length, content))
    }
}
