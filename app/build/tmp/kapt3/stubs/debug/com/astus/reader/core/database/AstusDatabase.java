package com.astus.reader.core.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.astus.reader.core.database.dao.BookDao;
import com.astus.reader.core.database.entity.BookEntity;
import com.astus.reader.core.database.entity.BookmarkEntity;
import com.astus.reader.core.database.entity.ReadingProgressEntity;
import com.astus.reader.core.database.entity.TtsProgressEntity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0005"}, d2 = {"Lcom/astus/reader/core/database/AstusDatabase;", "Landroidx/room/RoomDatabase;", "()V", "bookDao", "Lcom/astus/reader/core/database/dao/BookDao;", "app_debug"})
@androidx.room.Database(entities = {com.astus.reader.core.database.entity.BookEntity.class, com.astus.reader.core.database.entity.BookmarkEntity.class, com.astus.reader.core.database.entity.ReadingProgressEntity.class, com.astus.reader.core.database.entity.TtsProgressEntity.class}, version = 2, exportSchema = false)
public abstract class AstusDatabase extends androidx.room.RoomDatabase {
    
    public AstusDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.astus.reader.core.database.dao.BookDao bookDao();
}