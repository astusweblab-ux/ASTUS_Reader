package com.astus.reader.domain.model

import com.astus.reader.core.ui.ReaderThemeMode

data class ReadingProgress(
    val bookId: String,
    val position: Int = 0,
    val fontSizeSp: Float = 18f,
    val lineHeightMultiplier: Float = 1.55f,
    val themeMode: ReaderThemeMode = ReaderThemeMode.Light,
    val updatedAt: Long = System.currentTimeMillis()
)
