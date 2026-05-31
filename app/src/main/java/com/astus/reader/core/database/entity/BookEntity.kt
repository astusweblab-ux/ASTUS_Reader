package com.astus.reader.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id: String,
    val title: String,
    val author: String,
    val coverUri: String?,
    val sourceUri: String,
    val format: String,
    val content: String,
    val importedAt: Long
)
