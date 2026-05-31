package com.astus.reader.feature_reader

import android.content.Intent
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.astus.reader.core.tts.TtsPlaybackService
import com.astus.reader.core.ui.AstusReaderTheme
import com.astus.reader.core.ui.ReaderThemeMode
import com.astus.reader.feature_tts.TtsMiniPlayer
import kotlinx.coroutines.withTimeoutOrNull

private val ReaderGold = Color(0xFFE7BD63)

private data class ReaderPalette(
    val background: Color,
    val panel: Color,
    val text: Color,
    val muted: Color,
    val highlight: Color,
    val highlightText: Color
)

private fun paletteFor(mode: ReaderThemeMode): ReaderPalette = when (mode) {
    ReaderThemeMode.Light -> ReaderPalette(
        background = Color(0xFFFCFBF4),
        panel = Color(0xFFEDE6D5),
        text = Color(0xFF242019),
        muted = Color(0xFF776A55),
        highlight = Color(0xFFE8C36D),
        highlightText = Color(0xFF211706)
    )
    ReaderThemeMode.Dark -> ReaderPalette(
        background = Color(0xFF11120E),
        panel = Color(0xFF1E1C15),
        text = Color(0xFFF3E8D1),
        muted = Color(0xFFB8AA8C),
        highlight = ReaderGold,
        highlightText = Color(0xFF211706)
    )
    ReaderThemeMode.Amoled -> ReaderPalette(
        background = Color.Black,
        panel = Color(0xFF0A0A0A),
        text = Color(0xFFF0F0EA),
        muted = Color(0xFFA7A195),
        highlight = ReaderGold,
        highlightText = Color(0xFF211706)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderScreen(
    viewModel: ReaderViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val hapticFeedback = LocalHapticFeedback.current
    var ttsServiceStarted by remember { mutableStateOf(false) }
    var showPlayer by remember { mutableStateOf(false) }

    LaunchedEffect(state.pageCount, state.initialPageIndex) {
        if (state.pageCount > 0) {
            listState.scrollToItem(0)
        }
    }

    LaunchedEffect(state.isPlaying, state.book?.title) {
        if (state.isPlaying) {
            ttsServiceStarted = true
            ContextCompat.startForegroundService(
                context,
                TtsPlaybackService.startIntent(context, state.book?.title ?: "ASTUS Reader")
            )
        } else if (ttsServiceStarted) {
            context.startService(Intent(context, TtsPlaybackService::class.java).setAction(TtsPlaybackService.ACTION_PAUSE))
        }
    }

    LaunchedEffect(state.isPlaying, state.currentSentenceIndex) {
        if (state.isPlaying && state.currentPageSentences.isNotEmpty()) {
            val localIndex = state.currentPageSentences.indexOfFirst { it.index == state.currentSentenceIndex }
            if (localIndex >= 0) {
                val headerItems = 2 + if (state.bookmarks.isNotEmpty()) 1 else 0
                listState.animateScrollToItem(localIndex + headerItems)
            }
        }
    }

    val readerPalette = paletteFor(state.themeMode)

    AstusReaderTheme(mode = state.themeMode) {
        Scaffold(
            containerColor = readerPalette.background,
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = readerPalette.background,
                        titleContentColor = readerPalette.text,
                        navigationIconContentColor = readerPalette.text,
                        actionIconContentColor = readerPalette.text
                    ),
                    title = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = state.book?.title ?: "Чтение",
                                color = readerPalette.text,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = state.book?.author.orEmpty(),
                                color = readerPalette.muted,
                                style = MaterialTheme.typography.labelMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                    actions = {
                        IconButton(onClick = { viewModel.onIntent(ReaderIntent.AddBookmark) }) {
                            Icon(Icons.Default.BookmarkAdd, contentDescription = "Add bookmark")
                        }
                ReaderOptionsMenu(
                    state = state,
                    showPlayer = showPlayer,
                    onTogglePlayer = { showPlayer = !showPlayer },
                    onIntent = viewModel::onIntent
                )
            }
                )
            },
            bottomBar = {
                Column {
                    PageProgressFooter(
                        state = state,
                        palette = readerPalette,
                        applyNavigationPadding = !showPlayer
                    )
                    if (showPlayer) {
                        TtsMiniPlayer(
                            isPlaying = state.isPlaying,
                            speed = state.ttsSpeed,
                            pitch = state.ttsPitch,
                            themeMode = state.themeMode,
                            progress = state.readingProgress,
                            onPlayPause = { viewModel.onIntent(ReaderIntent.PlayPause) },
                            onPrevious = { viewModel.onIntent(ReaderIntent.PreviousSentence) },
                            onNext = { viewModel.onIntent(ReaderIntent.NextSentence) },
                            onSpeedChange = { viewModel.onIntent(ReaderIntent.SpeedChanged(it)) },
                            onPitchChange = { viewModel.onIntent(ReaderIntent.PitchChanged(it)) }
                        )
                    }
                }
            }
        ) { padding ->
            var pageDragAmount = 0f
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(readerPalette.background)
                    .pointerInput(state.currentPageIndex, state.pageCount) {
                        detectHorizontalDragGestures(
                            onDragStart = { pageDragAmount = 0f },
                            onHorizontalDrag = { _, dragAmount -> pageDragAmount += dragAmount },
                            onDragEnd = {
                                when {
                                    pageDragAmount <= -80f -> viewModel.onIntent(ReaderIntent.NextPage)
                                    pageDragAmount >= 80f -> viewModel.onIntent(ReaderIntent.PreviousPage)
                                }
                                pageDragAmount = 0f
                            },
                            onDragCancel = { pageDragAmount = 0f }
                        )
                    }
                    .padding(padding),
                contentPadding = PaddingValues(horizontal = 26.dp, vertical = 14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Text(
                        text = "Глава 1",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        color = readerPalette.muted,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                if (state.bookmarks.isNotEmpty()) {
                    item {
                        BookmarkPanel(
                            state = state,
                            onJump = { position ->
                                val index = state.sentences.indexOfLast { it.start <= position }.coerceAtLeast(0)
                                viewModel.onIntent(ReaderIntent.VisibleSentenceChanged(index))
                            },
                            onDelete = { viewModel.onIntent(ReaderIntent.DeleteBookmark(it)) }
                        )
                    }
                }
                itemsIndexed(state.currentPageSentences, key = { _, sentence -> sentence.index }) { _, sentence ->
                    val index = sentence.index
                    val selected = index == state.currentSentenceIndex
                    Text(
                        text = sentence.text,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = if (selected) readerPalette.highlight else Color.Transparent,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .longPressToSelect {
                                hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                                viewModel.onIntent(ReaderIntent.SelectSentence(index))
                            }
                            .padding(horizontal = 8.dp, vertical = 5.dp),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontFamily = FontFamily.Serif,
                            fontSize = state.fontSizeSp.sp,
                            lineHeight = (state.fontSizeSp * state.lineHeightMultiplier).sp,
                            color = if (selected) readerPalette.highlightText else readerPalette.text
                        )
                    )
                }
                item { Spacer(Modifier.height(60.dp)) }
            }
        }
    }
}

@Composable
private fun PageProgressFooter(
    state: ReaderState,
    palette: ReaderPalette,
    applyNavigationPadding: Boolean
) {
    val modifier = if (applyNavigationPadding) {
        Modifier.navigationBarsPadding()
    } else {
        Modifier
    }
    Surface(
        color = palette.panel,
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            LinearProgressIndicator(
                progress = { state.readingProgress.coerceIn(0f, 1f) },
                modifier = Modifier.fillMaxWidth(),
                color = ReaderGold,
                trackColor = palette.muted.copy(alpha = 0.28f)
            )
            Text(
                text = if (state.pageCount == 0) {
                    "Страница 0 из 0"
                } else {
                    "Страница ${state.currentPageNumber} из ${state.pageCount}"
                },
                modifier = Modifier.fillMaxWidth(),
                color = palette.text,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

private fun Modifier.longPressToSelect(
    durationMillis: Long = 1_000L,
    moveTolerancePx: Float = 18f,
    onLongPress: () -> Unit
): Modifier = pointerInput(onLongPress) {
    while (true) {
        awaitPointerEventScope {
            val down = awaitPointerEvent().changes.firstOrNull { it.pressed } ?: return@awaitPointerEventScope
            val pointerId = down.id
            var totalMove = Offset.Zero
            var cancelled = false

            val completed = withTimeoutOrNull(durationMillis) {
                while (true) {
                    val event = awaitPointerEvent()
                    val change = event.changes.firstOrNull { it.id == pointerId } ?: continue
                    if (!change.pressed) {
                        cancelled = true
                        return@withTimeoutOrNull false
                    }
                    totalMove += change.positionChange()
                    if (totalMove.getDistance() > moveTolerancePx) {
                        cancelled = true
                        return@withTimeoutOrNull false
                    }
                }
            }

            if (completed == null && !cancelled) {
                onLongPress()
                while (true) {
                    val event = awaitPointerEvent()
                    val change = event.changes.firstOrNull { it.id == pointerId }
                    if (change == null || !change.pressed) break
                    change.consume()
                }
            }
        }
    }
}

@Composable
private fun ReaderOptionsMenu(
    state: ReaderState,
    showPlayer: Boolean,
    onTogglePlayer: () -> Unit,
    onIntent: (ReaderIntent) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var themeExpanded by remember { mutableStateOf(false) }
    var engineExpanded by remember { mutableStateOf(false) }
    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Reader settings")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = { Text("Тема: ${state.themeMode.name}") },
                onClick = { themeExpanded = true }
            )
            DropdownMenuItem(
                text = { Text("Движок TTS") },
                onClick = { engineExpanded = true }
            )
            DropdownMenuItem(
                text = { Text(if (showPlayer) "Скрыть плеер" else "Показать плеер") },
                onClick = {
                    onTogglePlayer()
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Экран не гаснет")
                        Switch(
                            checked = state.keepScreenOn,
                            onCheckedChange = { onIntent(ReaderIntent.KeepScreenOnChanged(it)) }
                        )
                    }
                },
                onClick = { onIntent(ReaderIntent.KeepScreenOnChanged(!state.keepScreenOn)) }
            )
            DropdownMenuItem(
                text = {
                    Column {
                        Text("Размер шрифта ${state.fontSizeSp.toInt()}sp")
                        Slider(
                            value = state.fontSizeSp,
                            onValueChange = { onIntent(ReaderIntent.FontSizeChanged(it)) },
                            valueRange = 14f..30f,
                            steps = 15
                        )
                    }
                },
                onClick = {}
            )
            DropdownMenuItem(
                text = {
                    Column {
                        Text("Интервал ${"%.2f".format(state.lineHeightMultiplier)}")
                        Slider(
                            value = state.lineHeightMultiplier,
                            onValueChange = { onIntent(ReaderIntent.LineHeightChanged(it)) },
                            valueRange = 1.1f..2.2f,
                            steps = 10
                        )
                    }
                },
                onClick = {}
            )
        }
        DropdownMenu(expanded = themeExpanded, onDismissRequest = { themeExpanded = false }) {
            ReaderThemeMode.entries.forEach { mode ->
                DropdownMenuItem(
                    text = { Text(mode.name) },
                    onClick = {
                        onIntent(ReaderIntent.ThemeChanged(mode))
                        themeExpanded = false
                        expanded = false
                    }
                )
            }
        }
        DropdownMenu(expanded = engineExpanded, onDismissRequest = { engineExpanded = false }) {
            if (state.engines.isEmpty()) {
                DropdownMenuItem(text = { Text("Системный движок") }, onClick = {
                    onIntent(ReaderIntent.EngineChanged(null))
                    engineExpanded = false
                    expanded = false
                })
            }
            state.engines.forEach { engine ->
                DropdownMenuItem(
                    text = { Text(engine.label) },
                    onClick = {
                        onIntent(ReaderIntent.EngineChanged(engine.packageName))
                        engineExpanded = false
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun BookmarkPanel(
    state: ReaderState,
    onJump: (Int) -> Unit,
    onDelete: (String) -> Unit
) {
    val palette = paletteFor(state.themeMode)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(palette.panel, RoundedCornerShape(8.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Закладки", color = ReaderGold, style = MaterialTheme.typography.titleMedium)
        state.bookmarks.forEach { bookmark ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onJump(bookmark.position) },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ElevatedAssistChip(
                    onClick = { onJump(bookmark.position) },
                    label = { Text("${bookmark.position.coerceAtLeast(0)}") }
                )
                Text(
                    text = bookmark.title,
                    modifier = Modifier.weight(1f),
                    color = palette.text,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(onClick = { onDelete(bookmark.id) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete bookmark")
                }
            }
        }
    }
}
