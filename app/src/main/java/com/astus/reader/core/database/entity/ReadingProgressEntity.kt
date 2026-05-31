package com.astus.reader.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reading_progress")
data class ReadingProgressEntity(
    @PrimaryKey val bookId: String,
    val position: Int,
    val fontSizeSp: Float,
    val lineHeightMultiplier: Float,
    val themeMode: String,
    val updatedAt: Long
)
