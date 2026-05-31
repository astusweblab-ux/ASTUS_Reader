package com.astus.reader.feature_tts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.astus.reader.core.ui.ReaderThemeMode

private val PlayerGold = Color(0xFFE7BD63)

private data class PlayerPalette(
    val background: Color,
    val text: Color,
    val muted: Color,
    val track: Color
)

private fun playerPaletteFor(mode: ReaderThemeMode): PlayerPalette = when (mode) {
    ReaderThemeMode.Light -> PlayerPalette(
        background = Color(0xFFF3EDDE),
        text = Color(0xFF242019),
        muted = Color(0xFF776A55),
        track = Color(0xFFD5C8AA)
    )
    ReaderThemeMode.Dark -> PlayerPalette(
        background = Color(0xFF11120E),
        text = Color(0xFFF3E8D1),
        muted = Color(0xFFB8AA8C),
        track = Color(0xFF4B4435)
    )
    ReaderThemeMode.Amoled -> PlayerPalette(
        background = Color.Black,
        text = Color(0xFFF0F0EA),
        muted = Color(0xFFA7A195),
        track = Color(0xFF30291E)
    )
}

@Composable
fun TtsMiniPlayer(
    isPlaying: Boolean,
    speed: Float,
    pitch: Float,
    themeMode: ReaderThemeMode,
    progress: Float,
    onPlayPause: () -> Unit,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onSpeedChange: (Float) -> Unit,
    onPitchChange: (Float) -> Unit
) {
    var controlsExpanded by remember { mutableStateOf(false) }
    val palette = playerPaletteFor(themeMode)

    Surface(
        tonalElevation = 0.dp,
        shadowElevation = 10.dp,
        color = palette.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LinearProgressIndicator(
                progress = { progress.coerceIn(0f, 1f) },
                modifier = Modifier.fillMaxWidth(),
                color = PlayerGold,
                trackColor = palette.track
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onPrevious) {
                        Icon(Icons.Default.SkipPrevious, contentDescription = "Previous sentence", tint = palette.text)
                    }
                    IconButton(
                        onClick = onPlayPause,
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(PlayerGold)
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) "Pause" else "Play",
                            tint = Color(0xFF1A1205)
                        )
                    }
                    IconButton(onClick = onNext) {
                        Icon(Icons.Default.SkipNext, contentDescription = "Next sentence", tint = palette.text)
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    AssistChip(onClick = { controlsExpanded = !controlsExpanded }, label = { Text("${"%.2f".format(speed)}x") })
                    IconButton(onClick = { controlsExpanded = !controlsExpanded }) {
                        Icon(Icons.Default.Settings, contentDescription = "Playback settings", tint = palette.text)
                    }
                }
            }
            if (controlsExpanded) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Скорость", color = palette.muted, style = MaterialTheme.typography.labelMedium)
                    Slider(
                        value = speed,
                        onValueChange = onSpeedChange,
                        valueRange = 0.5f..2.5f,
                        steps = 15,
                        colors = goldSliderColors(palette.track, palette.muted),
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Интонация", color = palette.muted, style = MaterialTheme.typography.labelMedium)
                    Slider(
                        value = pitch,
                        onValueChange = onPitchChange,
                        valueRange = 0.5f..2.0f,
                        steps = 11,
                        colors = goldSliderColors(palette.track, palette.muted),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun goldSliderColors(track: Color, muted: Color) = SliderDefaults.colors(
    thumbColor = PlayerGold,
    activeTrackColor = PlayerGold,
    inactiveTrackColor = track,
    activeTickColor = Color(0xFF1A1205),
    inactiveTickColor = muted
)
