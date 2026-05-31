package com.astus.reader.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tts_progress")
data class TtsProgressEntity(
    @PrimaryKey val bookId: String,
    val sentenceIndex: Int,
    val charPosition: Int,
    val speed: Float,
    val pitch: Float = 1.0f,
    val enginePackage: String?,
    val updatedAt: Long
)
