package com.astus.reader.domain.model

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val coverUri: String?,
    val sourceUri: String,
    val format: String,
    val content: String,
    val importedAt: Long
)
