package com.astus.reader.data

import com.astus.reader.core.database.entity.BookEntity
import com.astus.reader.core.database.entity.BookmarkEntity
import com.astus.reader.core.database.entity.ReadingProgressEntity
import com.astus.reader.core.database.entity.TtsProgressEntity
import com.astus.reader.core.ui.ReaderThemeMode
import com.astus.reader.core.util.ParsedBook
import com.astus.reader.domain.model.Book
import com.astus.reader.domain.model.Bookmark
import com.astus.reader.domain.model.ReadingProgress
import com.astus.reader.domain.model.TtsProgress

fun BookEntity.toDomain() = Book(id, title, author, coverUri, sourceUri, format, content, importedAt)

fun ParsedBook.toEntity() = BookEntity(id, title, author, coverUri, sourceUri, format, content, importedAt)

fun BookmarkEntity.toDomain() = Bookmark(id, bookId, position, title, createdAt)

fun ReadingProgressEntity.toDomain() = ReadingProgress(
    bookId = bookId,
    position = position,
    fontSizeSp = fontSizeSp,
    lineHeightMultiplier = lineHeightMultiplier,
    themeMode = runCatching { ReaderThemeMode.valueOf(themeMode) }.getOrDefault(ReaderThemeMode.Light),
    updatedAt = updatedAt
)

fun ReadingProgress.toEntity() = ReadingProgressEntity(
    bookId = bookId,
    position = position,
    fontSizeSp = fontSizeSp,
    lineHeightMultiplier = lineHeightMultiplier,
    themeMode = themeMode.name,
    updatedAt = updatedAt
)

fun TtsProgressEntity.toDomain() = TtsProgress(bookId, sentenceIndex, charPosition, speed, pitch, enginePackage, updatedAt)

fun TtsProgress.toEntity() = TtsProgressEntity(bookId, sentenceIndex, charPosition, speed, pitch, enginePackage, updatedAt)
