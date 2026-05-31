package com.astus.reader.core.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.EntityUpsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.astus.reader.core.database.entity.BookEntity;
import com.astus.reader.core.database.entity.BookmarkEntity;
import com.astus.reader.core.database.entity.ReadingProgressEntity;
import com.astus.reader.core.database.entity.TtsProgressEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class BookDao_Impl implements BookDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BookEntity> __insertionAdapterOfBookEntity;

  private final EntityInsertionAdapter<BookmarkEntity> __insertionAdapterOfBookmarkEntity;

  private final EntityDeletionOrUpdateAdapter<BookEntity> __deletionAdapterOfBookEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateBookCover;

  private final SharedSQLiteStatement __preparedStmtOfDeleteBookmark;

  private final EntityUpsertionAdapter<ReadingProgressEntity> __upsertionAdapterOfReadingProgressEntity;

  private final EntityUpsertionAdapter<TtsProgressEntity> __upsertionAdapterOfTtsProgressEntity;

  public BookDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBookEntity = new EntityInsertionAdapter<BookEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `books` (`id`,`title`,`author`,`coverUri`,`sourceUri`,`format`,`content`,`importedAt`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BookEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getAuthor() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getAuthor());
        }
        if (entity.getCoverUri() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCoverUri());
        }
        if (entity.getSourceUri() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getSourceUri());
        }
        if (entity.getFormat() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getFormat());
        }
        if (entity.getContent() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getContent());
        }
        statement.bindLong(8, entity.getImportedAt());
      }
    };
    this.__insertionAdapterOfBookmarkEntity = new EntityInsertionAdapter<BookmarkEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `bookmarks` (`id`,`bookId`,`position`,`title`,`createdAt`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BookmarkEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getBookId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getBookId());
        }
        statement.bindLong(3, entity.getPosition());
        if (entity.getTitle() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTitle());
        }
        statement.bindLong(5, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfBookEntity = new EntityDeletionOrUpdateAdapter<BookEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `books` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BookEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
      }
    };
    this.__preparedStmtOfUpdateBookCover = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE books SET coverUri = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteBookmark = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM bookmarks WHERE id = ?";
        return _query;
      }
    };
    this.__upsertionAdapterOfReadingProgressEntity = new EntityUpsertionAdapter<ReadingProgressEntity>(new EntityInsertionAdapter<ReadingProgressEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `reading_progress` (`bookId`,`position`,`fontSizeSp`,`lineHeightMultiplier`,`themeMode`,`updatedAt`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ReadingProgressEntity entity) {
        if (entity.getBookId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getBookId());
        }
        statement.bindLong(2, entity.getPosition());
        statement.bindDouble(3, entity.getFontSizeSp());
        statement.bindDouble(4, entity.getLineHeightMultiplier());
        if (entity.getThemeMode() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getThemeMode());
        }
        statement.bindLong(6, entity.getUpdatedAt());
      }
    }, new EntityDeletionOrUpdateAdapter<ReadingProgressEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `reading_progress` SET `bookId` = ?,`position` = ?,`fontSizeSp` = ?,`lineHeightMultiplier` = ?,`themeMode` = ?,`updatedAt` = ? WHERE `bookId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ReadingProgressEntity entity) {
        if (entity.getBookId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getBookId());
        }
        statement.bindLong(2, entity.getPosition());
        statement.bindDouble(3, entity.getFontSizeSp());
        statement.bindDouble(4, entity.getLineHeightMultiplier());
        if (entity.getThemeMode() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getThemeMode());
        }
        statement.bindLong(6, entity.getUpdatedAt());
        if (entity.getBookId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getBookId());
        }
      }
    });
    this.__upsertionAdapterOfTtsProgressEntity = new EntityUpsertionAdapter<TtsProgressEntity>(new EntityInsertionAdapter<TtsProgressEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `tts_progress` (`bookId`,`sentenceIndex`,`charPosition`,`speed`,`pitch`,`enginePackage`,`updatedAt`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TtsProgressEntity entity) {
        if (entity.getBookId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getBookId());
        }
        statement.bindLong(2, entity.getSentenceIndex());
        statement.bindLong(3, entity.getCharPosition());
        statement.bindDouble(4, entity.getSpeed());
        statement.bindDouble(5, entity.getPitch());
        if (entity.getEnginePackage() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getEnginePackage());
        }
        statement.bindLong(7, entity.getUpdatedAt());
      }
    }, new EntityDeletionOrUpdateAdapter<TtsProgressEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `tts_progress` SET `bookId` = ?,`sentenceIndex` = ?,`charPosition` = ?,`speed` = ?,`pitch` = ?,`enginePackage` = ?,`updatedAt` = ? WHERE `bookId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TtsProgressEntity entity) {
        if (entity.getBookId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getBookId());
        }
        statement.bindLong(2, entity.getSentenceIndex());
        statement.bindLong(3, entity.getCharPosition());
        statement.bindDouble(4, entity.getSpeed());
        statement.bindDouble(5, entity.getPitch());
        if (entity.getEnginePackage() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getEnginePackage());
        }
        statement.bindLong(7, entity.getUpdatedAt());
        if (entity.getBookId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getBookId());
        }
      }
    });
  }

  @Override
  public Object upsertBooks(final List<BookEntity> books,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBookEntity.insert(books);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertBookmark(final BookmarkEntity bookmark,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBookmarkEntity.insert(bookmark);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteBook(final BookEntity book, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfBookEntity.handle(book);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateBookCover(final String bookId, final String coverUri,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateBookCover.acquire();
        int _argIndex = 1;
        if (coverUri == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, coverUri);
        }
        _argIndex = 2;
        if (bookId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, bookId);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateBookCover.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteBookmark(final String bookmarkId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteBookmark.acquire();
        int _argIndex = 1;
        if (bookmarkId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, bookmarkId);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteBookmark.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertReadingProgress(final ReadingProgressEntity progress,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfReadingProgressEntity.upsert(progress);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertTtsProgress(final TtsProgressEntity progress,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfTtsProgressEntity.upsert(progress);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<BookEntity>> observeBooks() {
    final String _sql = "SELECT * FROM books ORDER BY importedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"books"}, new Callable<List<BookEntity>>() {
      @Override
      @NonNull
      public List<BookEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfCoverUri = CursorUtil.getColumnIndexOrThrow(_cursor, "coverUri");
          final int _cursorIndexOfSourceUri = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceUri");
          final int _cursorIndexOfFormat = CursorUtil.getColumnIndexOrThrow(_cursor, "format");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfImportedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "importedAt");
          final List<BookEntity> _result = new ArrayList<BookEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BookEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpAuthor;
            if (_cursor.isNull(_cursorIndexOfAuthor)) {
              _tmpAuthor = null;
            } else {
              _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            }
            final String _tmpCoverUri;
            if (_cursor.isNull(_cursorIndexOfCoverUri)) {
              _tmpCoverUri = null;
            } else {
              _tmpCoverUri = _cursor.getString(_cursorIndexOfCoverUri);
            }
            final String _tmpSourceUri;
            if (_cursor.isNull(_cursorIndexOfSourceUri)) {
              _tmpSourceUri = null;
            } else {
              _tmpSourceUri = _cursor.getString(_cursorIndexOfSourceUri);
            }
            final String _tmpFormat;
            if (_cursor.isNull(_cursorIndexOfFormat)) {
              _tmpFormat = null;
            } else {
              _tmpFormat = _cursor.getString(_cursorIndexOfFormat);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final long _tmpImportedAt;
            _tmpImportedAt = _cursor.getLong(_cursorIndexOfImportedAt);
            _item = new BookEntity(_tmpId,_tmpTitle,_tmpAuthor,_tmpCoverUri,_tmpSourceUri,_tmpFormat,_tmpContent,_tmpImportedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<BookEntity> observeBook(final String bookId) {
    final String _sql = "SELECT * FROM books WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (bookId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, bookId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"books"}, new Callable<BookEntity>() {
      @Override
      @Nullable
      public BookEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfCoverUri = CursorUtil.getColumnIndexOrThrow(_cursor, "coverUri");
          final int _cursorIndexOfSourceUri = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceUri");
          final int _cursorIndexOfFormat = CursorUtil.getColumnIndexOrThrow(_cursor, "format");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfImportedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "importedAt");
          final BookEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpAuthor;
            if (_cursor.isNull(_cursorIndexOfAuthor)) {
              _tmpAuthor = null;
            } else {
              _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            }
            final String _tmpCoverUri;
            if (_cursor.isNull(_cursorIndexOfCoverUri)) {
              _tmpCoverUri = null;
            } else {
              _tmpCoverUri = _cursor.getString(_cursorIndexOfCoverUri);
            }
            final String _tmpSourceUri;
            if (_cursor.isNull(_cursorIndexOfSourceUri)) {
              _tmpSourceUri = null;
            } else {
              _tmpSourceUri = _cursor.getString(_cursorIndexOfSourceUri);
            }
            final String _tmpFormat;
            if (_cursor.isNull(_cursorIndexOfFormat)) {
              _tmpFormat = null;
            } else {
              _tmpFormat = _cursor.getString(_cursorIndexOfFormat);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final long _tmpImportedAt;
            _tmpImportedAt = _cursor.getLong(_cursorIndexOfImportedAt);
            _result = new BookEntity(_tmpId,_tmpTitle,_tmpAuthor,_tmpCoverUri,_tmpSourceUri,_tmpFormat,_tmpContent,_tmpImportedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getBook(final String bookId, final Continuation<? super BookEntity> $completion) {
    final String _sql = "SELECT * FROM books WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (bookId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, bookId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<BookEntity>() {
      @Override
      @Nullable
      public BookEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfCoverUri = CursorUtil.getColumnIndexOrThrow(_cursor, "coverUri");
          final int _cursorIndexOfSourceUri = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceUri");
          final int _cursorIndexOfFormat = CursorUtil.getColumnIndexOrThrow(_cursor, "format");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfImportedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "importedAt");
          final BookEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpAuthor;
            if (_cursor.isNull(_cursorIndexOfAuthor)) {
              _tmpAuthor = null;
            } else {
              _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            }
            final String _tmpCoverUri;
            if (_cursor.isNull(_cursorIndexOfCoverUri)) {
              _tmpCoverUri = null;
            } else {
              _tmpCoverUri = _cursor.getString(_cursorIndexOfCoverUri);
            }
            final String _tmpSourceUri;
            if (_cursor.isNull(_cursorIndexOfSourceUri)) {
              _tmpSourceUri = null;
            } else {
              _tmpSourceUri = _cursor.getString(_cursorIndexOfSourceUri);
            }
            final String _tmpFormat;
            if (_cursor.isNull(_cursorIndexOfFormat)) {
              _tmpFormat = null;
            } else {
              _tmpFormat = _cursor.getString(_cursorIndexOfFormat);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final long _tmpImportedAt;
            _tmpImportedAt = _cursor.getLong(_cursorIndexOfImportedAt);
            _result = new BookEntity(_tmpId,_tmpTitle,_tmpAuthor,_tmpCoverUri,_tmpSourceUri,_tmpFormat,_tmpContent,_tmpImportedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<BookmarkEntity>> observeBookmarks(final String bookId) {
    final String _sql = "SELECT * FROM bookmarks WHERE bookId = ? ORDER BY position ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (bookId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, bookId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"bookmarks"}, new Callable<List<BookmarkEntity>>() {
      @Override
      @NonNull
      public List<BookmarkEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBookId = CursorUtil.getColumnIndexOrThrow(_cursor, "bookId");
          final int _cursorIndexOfPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "position");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<BookmarkEntity> _result = new ArrayList<BookmarkEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BookmarkEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpBookId;
            if (_cursor.isNull(_cursorIndexOfBookId)) {
              _tmpBookId = null;
            } else {
              _tmpBookId = _cursor.getString(_cursorIndexOfBookId);
            }
            final int _tmpPosition;
            _tmpPosition = _cursor.getInt(_cursorIndexOfPosition);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new BookmarkEntity(_tmpId,_tmpBookId,_tmpPosition,_tmpTitle,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<ReadingProgressEntity> observeReadingProgress(final String bookId) {
    final String _sql = "SELECT * FROM reading_progress WHERE bookId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (bookId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, bookId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"reading_progress"}, new Callable<ReadingProgressEntity>() {
      @Override
      @Nullable
      public ReadingProgressEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfBookId = CursorUtil.getColumnIndexOrThrow(_cursor, "bookId");
          final int _cursorIndexOfPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "position");
          final int _cursorIndexOfFontSizeSp = CursorUtil.getColumnIndexOrThrow(_cursor, "fontSizeSp");
          final int _cursorIndexOfLineHeightMultiplier = CursorUtil.getColumnIndexOrThrow(_cursor, "lineHeightMultiplier");
          final int _cursorIndexOfThemeMode = CursorUtil.getColumnIndexOrThrow(_cursor, "themeMode");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final ReadingProgressEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpBookId;
            if (_cursor.isNull(_cursorIndexOfBookId)) {
              _tmpBookId = null;
            } else {
              _tmpBookId = _cursor.getString(_cursorIndexOfBookId);
            }
            final int _tmpPosition;
            _tmpPosition = _cursor.getInt(_cursorIndexOfPosition);
            final float _tmpFontSizeSp;
            _tmpFontSizeSp = _cursor.getFloat(_cursorIndexOfFontSizeSp);
            final float _tmpLineHeightMultiplier;
            _tmpLineHeightMultiplier = _cursor.getFloat(_cursorIndexOfLineHeightMultiplier);
            final String _tmpThemeMode;
            if (_cursor.isNull(_cursorIndexOfThemeMode)) {
              _tmpThemeMode = null;
            } else {
              _tmpThemeMode = _cursor.getString(_cursorIndexOfThemeMode);
            }
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new ReadingProgressEntity(_tmpBookId,_tmpPosition,_tmpFontSizeSp,_tmpLineHeightMultiplier,_tmpThemeMode,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getReadingProgress(final String bookId,
      final Continuation<? super ReadingProgressEntity> $completion) {
    final String _sql = "SELECT * FROM reading_progress WHERE bookId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (bookId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, bookId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ReadingProgressEntity>() {
      @Override
      @Nullable
      public ReadingProgressEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfBookId = CursorUtil.getColumnIndexOrThrow(_cursor, "bookId");
          final int _cursorIndexOfPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "position");
          final int _cursorIndexOfFontSizeSp = CursorUtil.getColumnIndexOrThrow(_cursor, "fontSizeSp");
          final int _cursorIndexOfLineHeightMultiplier = CursorUtil.getColumnIndexOrThrow(_cursor, "lineHeightMultiplier");
          final int _cursorIndexOfThemeMode = CursorUtil.getColumnIndexOrThrow(_cursor, "themeMode");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final ReadingProgressEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpBookId;
            if (_cursor.isNull(_cursorIndexOfBookId)) {
              _tmpBookId = null;
            } else {
              _tmpBookId = _cursor.getString(_cursorIndexOfBookId);
            }
            final int _tmpPosition;
            _tmpPosition = _cursor.getInt(_cursorIndexOfPosition);
            final float _tmpFontSizeSp;
            _tmpFontSizeSp = _cursor.getFloat(_cursorIndexOfFontSizeSp);
            final float _tmpLineHeightMultiplier;
            _tmpLineHeightMultiplier = _cursor.getFloat(_cursorIndexOfLineHeightMultiplier);
            final String _tmpThemeMode;
            if (_cursor.isNull(_cursorIndexOfThemeMode)) {
              _tmpThemeMode = null;
            } else {
              _tmpThemeMode = _cursor.getString(_cursorIndexOfThemeMode);
            }
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new ReadingProgressEntity(_tmpBookId,_tmpPosition,_tmpFontSizeSp,_tmpLineHeightMultiplier,_tmpThemeMode,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<TtsProgressEntity> observeTtsProgress(final String bookId) {
    final String _sql = "SELECT * FROM tts_progress WHERE bookId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (bookId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, bookId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tts_progress"}, new Callable<TtsProgressEntity>() {
      @Override
      @Nullable
      public TtsProgressEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfBookId = CursorUtil.getColumnIndexOrThrow(_cursor, "bookId");
          final int _cursorIndexOfSentenceIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "sentenceIndex");
          final int _cursorIndexOfCharPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "charPosition");
          final int _cursorIndexOfSpeed = CursorUtil.getColumnIndexOrThrow(_cursor, "speed");
          final int _cursorIndexOfPitch = CursorUtil.getColumnIndexOrThrow(_cursor, "pitch");
          final int _cursorIndexOfEnginePackage = CursorUtil.getColumnIndexOrThrow(_cursor, "enginePackage");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final TtsProgressEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpBookId;
            if (_cursor.isNull(_cursorIndexOfBookId)) {
              _tmpBookId = null;
            } else {
              _tmpBookId = _cursor.getString(_cursorIndexOfBookId);
            }
            final int _tmpSentenceIndex;
            _tmpSentenceIndex = _cursor.getInt(_cursorIndexOfSentenceIndex);
            final int _tmpCharPosition;
            _tmpCharPosition = _cursor.getInt(_cursorIndexOfCharPosition);
            final float _tmpSpeed;
            _tmpSpeed = _cursor.getFloat(_cursorIndexOfSpeed);
            final float _tmpPitch;
            _tmpPitch = _cursor.getFloat(_cursorIndexOfPitch);
            final String _tmpEnginePackage;
            if (_cursor.isNull(_cursorIndexOfEnginePackage)) {
              _tmpEnginePackage = null;
            } else {
              _tmpEnginePackage = _cursor.getString(_cursorIndexOfEnginePackage);
            }
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new TtsProgressEntity(_tmpBookId,_tmpSentenceIndex,_tmpCharPosition,_tmpSpeed,_tmpPitch,_tmpEnginePackage,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getTtsProgress(final String bookId,
      final Continuation<? super TtsProgressEntity> $completion) {
    final String _sql = "SELECT * FROM tts_progress WHERE bookId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (bookId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, bookId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<TtsProgressEntity>() {
      @Override
      @Nullable
      public TtsProgressEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfBookId = CursorUtil.getColumnIndexOrThrow(_cursor, "bookId");
          final int _cursorIndexOfSentenceIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "sentenceIndex");
          final int _cursorIndexOfCharPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "charPosition");
          final int _cursorIndexOfSpeed = CursorUtil.getColumnIndexOrThrow(_cursor, "speed");
          final int _cursorIndexOfPitch = CursorUtil.getColumnIndexOrThrow(_cursor, "pitch");
          final int _cursorIndexOfEnginePackage = CursorUtil.getColumnIndexOrThrow(_cursor, "enginePackage");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final TtsProgressEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpBookId;
            if (_cursor.isNull(_cursorIndexOfBookId)) {
              _tmpBookId = null;
            } else {
              _tmpBookId = _cursor.getString(_cursorIndexOfBookId);
            }
            final int _tmpSentenceIndex;
            _tmpSentenceIndex = _cursor.getInt(_cursorIndexOfSentenceIndex);
            final int _tmpCharPosition;
            _tmpCharPosition = _cursor.getInt(_cursorIndexOfCharPosition);
            final float _tmpSpeed;
            _tmpSpeed = _cursor.getFloat(_cursorIndexOfSpeed);
            final float _tmpPitch;
            _tmpPitch = _cursor.getFloat(_cursorIndexOfPitch);
            final String _tmpEnginePackage;
            if (_cursor.isNull(_cursorIndexOfEnginePackage)) {
              _tmpEnginePackage = null;
            } else {
              _tmpEnginePackage = _cursor.getString(_cursorIndexOfEnginePackage);
            }
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new TtsProgressEntity(_tmpBookId,_tmpSentenceIndex,_tmpCharPosition,_tmpSpeed,_tmpPitch,_tmpEnginePackage,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
