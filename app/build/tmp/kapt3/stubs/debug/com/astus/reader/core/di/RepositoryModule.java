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
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\u0007"}, d2 = {"Lcom/astus/reader/core/di/RepositoryModule;", "", "()V", "bindBookRepository", "Lcom/astus/reader/domain/repository/BookRepository;", "repository", "Lcom/astus/reader/data/BookRepositoryImpl;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class RepositoryModule {
    
    public RepositoryModule() {
        super();
    }
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.astus.reader.domain.repository.BookRepository bindBookRepository(@org.jetbrains.annotations.NotNull()
    com.astus.reader.data.BookRepositoryImpl repository);
}