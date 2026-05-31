package com.astus.reader.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.astus.reader.core.database.dao.BookDao
import com.astus.reader.core.database.entity.BookEntity
import com.astus.reader.core.database.entity.BookmarkEntity
import com.astus.reader.core.database.entity.ReadingProgressEntity
import com.astus.reader.core.database.entity.TtsProgressEntity

@Database(
    entities = [
        BookEntity::class,
        BookmarkEntity::class,
        ReadingProgressEntity::class,
        TtsProgressEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AstusDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}
