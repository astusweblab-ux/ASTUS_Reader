package com.astus.reader.domain.model

data class Bookmark(
    val id: String,
    val bookId: String,
    val position: Int,
    val title: String,
    val createdAt: Long
)
