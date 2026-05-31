package com.astus.reader.core.di;

import android.content.Context;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.astus.reader.core.database.AstusDatabase;
import com.astus.reader.core.database.dao.BookDao;
import com.astus.reader.data.BookRepositoryImpl;
import com.astus.reader.domain.repository.BookRepository;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0012\u0010\t\u001a\u00020\b2\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/astus/reader/core/di/DatabaseModule;", "", "()V", "Migration1To2", "Landroidx/room/migration/Migration;", "provideBookDao", "Lcom/astus/reader/core/database/dao/BookDao;", "database", "Lcom/astus/reader/core/database/AstusDatabase;", "provideDatabase", "context", "Landroid/content/Context;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DatabaseModule {
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration Migration1To2 = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.astus.reader.core.di.DatabaseModule INSTANCE = null;
    
    private DatabaseModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.astus.reader.core.database.AstusDatabase provideDatabase(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.astus.reader.core.database.dao.BookDao provideBookDao(@org.jetbrains.annotations.NotNull()
    com.astus.reader.core.database.AstusDatabase database) {
        return null;
    }
}