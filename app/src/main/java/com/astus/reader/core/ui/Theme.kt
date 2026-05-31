package com.astus.reader.core.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

enum class ReaderThemeMode {
    Light,
    Dark,
    Amoled
}

private val LightColors = lightColorScheme(
    primary = Color(0xFF4F5D2F),
    secondary = Color(0xFF6D5944),
    tertiary = Color(0xFF006A67),
    background = Color(0xFFFCFBF4),
    surface = Color(0xFFFCFBF4),
    surfaceVariant = Color(0xFFE8E2D2),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFF1D1C18),
    onSurface = Color(0xFF1D1C18)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFE7BD63),
    secondary = Color(0xFFB8873C),
    tertiary = Color(0xFFEFD6A2),
    background = Color(0xFF0E0F0D),
    surface = Color(0xFF12130F),
    surfaceVariant = Color(0xFF242117),
    onPrimary = Color(0xFF1B1305),
    onSecondary = Color(0xFF1B1305),
    onBackground = Color(0xFFF2E8D4),
    onSurface = Color(0xFFF2E8D4)
)

private val AmoledColors = darkColorScheme(
    primary = Color(0xFFE7BD63),
    secondary = Color(0xFFB8873C),
    tertiary = Color(0xFFEFD6A2),
    background = Color.Black,
    surface = Color.Black,
    surfaceVariant = Color(0xFF1E1A12),
    onPrimary = Color(0xFF1B1305),
    onSecondary = Color(0xFF1B1305),
    onBackground = Color(0xFFF2E8D4),
    onSurface = Color(0xFFF2E8D4)
)

@Composable
fun AstusReaderTheme(
    mode: ReaderThemeMode? = null,
    content: @Composable () -> Unit
) {
    val scheme: ColorScheme = when (mode) {
        ReaderThemeMode.Light -> LightColors
        ReaderThemeMode.Dark -> DarkColors
        ReaderThemeMode.Amoled -> AmoledColors
        null -> if (isSystemInDarkTheme()) DarkColors else LightColors
    }

    MaterialTheme(
        colorScheme = scheme,
        typography = AstusTypography,
        content = content
    )
}
