package com.astus.reader.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.astus.reader.core.database.AstusDatabase
import com.astus.reader.core.database.dao.BookDao
import com.astus.reader.data.BookRepositoryImpl
import com.astus.reader.domain.repository.BookRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private val Migration1To2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE tts_progress ADD COLUMN pitch REAL NOT NULL DEFAULT 1.0")
        }
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AstusDatabase =
        Room.databaseBuilder(context, AstusDatabase::class.java, "astus_reader.db")
            .addMigrations(Migration1To2)
            .build()

    @Provides
    fun provideBookDao(database: AstusDatabase): BookDao = database.bookDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindBookRepository(repository: BookRepositoryImpl): BookRepository
}
