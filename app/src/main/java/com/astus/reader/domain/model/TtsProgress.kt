package com.astus.reader.domain.model

data class TtsProgress(
    val bookId: String,
    val sentenceIndex: Int = 0,
    val charPosition: Int = 0,
    val speed: Float = 1.0f,
    val pitch: Float = 1.0f,
    val enginePackage: String? = null,
    val updatedAt: Long = System.currentTimeMillis()
)
