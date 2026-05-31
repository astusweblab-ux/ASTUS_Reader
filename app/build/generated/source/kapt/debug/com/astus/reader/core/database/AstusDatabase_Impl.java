package com.astus.reader.core.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.astus.reader.core.database.dao.BookDao;
import com.astus.reader.core.database.dao.BookDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AstusDatabase_Impl extends AstusDatabase {
  private volatile BookDao _bookDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `books` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `author` TEXT NOT NULL, `coverUri` TEXT, `sourceUri` TEXT NOT NULL, `format` TEXT NOT NULL, `content` TEXT NOT NULL, `importedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `bookmarks` (`id` TEXT NOT NULL, `bookId` TEXT NOT NULL, `position` INTEGER NOT NULL, `title` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`bookId`) REFERENCES `books`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_bookmarks_bookId` ON `bookmarks` (`bookId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `reading_progress` (`bookId` TEXT NOT NULL, `position` INTEGER NOT NULL, `fontSizeSp` REAL NOT NULL, `lineHeightMultiplier` REAL NOT NULL, `themeMode` TEXT NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`bookId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `tts_progress` (`bookId` TEXT NOT NULL, `sentenceIndex` INTEGER NOT NULL, `charPosition` INTEGER NOT NULL, `speed` REAL NOT NULL, `pitch` REAL NOT NULL, `enginePackage` TEXT, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`bookId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '1b29625394fec696de6e314ca975fc7d')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `books`");
        db.execSQL("DROP TABLE IF EXISTS `bookmarks`");
        db.execSQL("DROP TABLE IF EXISTS `reading_progress`");
        db.execSQL("DROP TABLE IF EXISTS `tts_progress`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsBooks = new HashMap<String, TableInfo.Column>(8);
        _columnsBooks.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBooks.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBooks.put("author", new TableInfo.Column("author", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBooks.put("coverUri", new TableInfo.Column("coverUri", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBooks.put("sourceUri", new TableInfo.Column("sourceUri", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBooks.put("format", new TableInfo.Column("format", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBooks.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBooks.put("importedAt", new TableInfo.Column("importedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBooks = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBooks = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBooks = new TableInfo("books", _columnsBooks, _foreignKeysBooks, _indicesBooks);
        final TableInfo _existingBooks = TableInfo.read(db, "books");
        if (!_infoBooks.equals(_existingBooks)) {
          return new RoomOpenHelper.ValidationResult(false, "books(com.astus.reader.core.database.entity.BookEntity).\n"
                  + " Expected:\n" + _infoBooks + "\n"
                  + " Found:\n" + _existingBooks);
        }
        final HashMap<String, TableInfo.Column> _columnsBookmarks = new HashMap<String, TableInfo.Column>(5);
        _columnsBookmarks.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookmarks.put("bookId", new TableInfo.Column("bookId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookmarks.put("position", new TableInfo.Column("position", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookmarks.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookmarks.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBookmarks = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysBookmarks.add(new TableInfo.ForeignKey("books", "CASCADE", "NO ACTION", Arrays.asList("bookId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesBookmarks = new HashSet<TableInfo.Index>(1);
        _indicesBookmarks.add(new TableInfo.Index("index_bookmarks_bookId", false, Arrays.asList("bookId"), Arrays.asList("ASC")));
        final TableInfo _infoBookmarks = new TableInfo("bookmarks", _columnsBookmarks, _foreignKeysBookmarks, _indicesBookmarks);
        final TableInfo _existingBookmarks = TableInfo.read(db, "bookmarks");
        if (!_infoBookmarks.equals(_existingBookmarks)) {
          return new RoomOpenHelper.ValidationResult(false, "bookmarks(com.astus.reader.core.database.entity.BookmarkEntity).\n"
                  + " Expected:\n" + _infoBookmarks + "\n"
                  + " Found:\n" + _existingBookmarks);
        }
        final HashMap<String, TableInfo.Column> _columnsReadingProgress = new HashMap<String, TableInfo.Column>(6);
        _columnsReadingProgress.put("bookId", new TableInfo.Column("bookId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReadingProgress.put("position", new TableInfo.Column("position", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReadingProgress.put("fontSizeSp", new TableInfo.Column("fontSizeSp", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReadingProgress.put("lineHeightMultiplier", new TableInfo.Column("lineHeightMultiplier", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReadingProgress.put("themeMode", new TableInfo.Column("themeMode", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReadingProgress.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReadingProgress = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReadingProgress = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReadingProgress = new TableInfo("reading_progress", _columnsReadingProgress, _foreignKeysReadingProgress, _indicesReadingProgress);
        final TableInfo _existingReadingProgress = TableInfo.read(db, "reading_progress");
        if (!_infoReadingProgress.equals(_existingReadingProgress)) {
          return new RoomOpenHelper.ValidationResult(false, "reading_progress(com.astus.reader.core.database.entity.ReadingProgressEntity).\n"
                  + " Expected:\n" + _infoReadingProgress + "\n"
                  + " Found:\n" + _existingReadingProgress);
        }
        final HashMap<String, TableInfo.Column> _columnsTtsProgress = new HashMap<String, TableInfo.Column>(7);
        _columnsTtsProgress.put("bookId", new TableInfo.Column("bookId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTtsProgress.put("sentenceIndex", new TableInfo.Column("sentenceIndex", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTtsProgress.put("charPosition", new TableInfo.Column("charPosition", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTtsProgress.put("speed", new TableInfo.Column("speed", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTtsProgress.put("pitch", new TableInfo.Column("pitch", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTtsProgress.put("enginePackage", new TableInfo.Column("enginePackage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTtsProgress.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTtsProgress = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTtsProgress = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTtsProgress = new TableInfo("tts_progress", _columnsTtsProgress, _foreignKeysTtsProgress, _indicesTtsProgress);
        final TableInfo _existingTtsProgress = TableInfo.read(db, "tts_progress");
        if (!_infoTtsProgress.equals(_existingTtsProgress)) {
          return new RoomOpenHelper.ValidationResult(false, "tts_progress(com.astus.reader.core.database.entity.TtsProgressEntity).\n"
                  + " Expected:\n" + _infoTtsProgress + "\n"
                  + " Found:\n" + _existingTtsProgress);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "1b29625394fec696de6e314ca975fc7d", "56a96d636b901d4ed2626ea3a3fcf24a");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "books","bookmarks","reading_progress","tts_progress");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `books`");
      _db.execSQL("DELETE FROM `bookmarks`");
      _db.execSQL("DELETE FROM `reading_progress`");
      _db.execSQL("DELETE FROM `tts_progress`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(BookDao.class, BookDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public BookDao bookDao() {
    if (_bookDao != null) {
      return _bookDao;
    } else {
      synchronized(this) {
        if(_bookDao == null) {
          _bookDao = new BookDao_Impl(this);
        }
        return _bookDao;
      }
    }
  }
}
