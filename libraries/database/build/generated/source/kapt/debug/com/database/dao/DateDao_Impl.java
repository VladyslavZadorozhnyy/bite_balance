package com.database.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.database.entities.DateEntity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DateDao_Impl implements DateDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DateEntity> __insertionAdapterOfDateEntity;

  private final EntityDeletionOrUpdateAdapter<DateEntity> __updateAdapterOfDateEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public DateDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDateEntity = new EntityInsertionAdapter<DateEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `date_table` (`minute`,`hour`,`day`,`month`,`year`,`additions`,`id`) VALUES (?,?,?,?,?,?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DateEntity value) {
        stmt.bindLong(1, value.getMinute());
        stmt.bindLong(2, value.getHour());
        stmt.bindLong(3, value.getDay());
        stmt.bindLong(4, value.getMonth());
        stmt.bindLong(5, value.getYear());
        if (value.getAdditions() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAdditions());
        }
        stmt.bindLong(7, value.getId());
      }
    };
    this.__updateAdapterOfDateEntity = new EntityDeletionOrUpdateAdapter<DateEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `date_table` SET `minute` = ?,`hour` = ?,`day` = ?,`month` = ?,`year` = ?,`additions` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DateEntity value) {
        stmt.bindLong(1, value.getMinute());
        stmt.bindLong(2, value.getHour());
        stmt.bindLong(3, value.getDay());
        stmt.bindLong(4, value.getMonth());
        stmt.bindLong(5, value.getYear());
        if (value.getAdditions() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAdditions());
        }
        stmt.bindLong(7, value.getId());
        stmt.bindLong(8, value.getId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM date_table WHERE id=?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM date_table";
        return _query;
      }
    };
  }

  @Override
  public long insert(final DateEntity dateEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfDateEntity.insertAndReturnId(dateEntity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateItem(final DateEntity dateEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfDateEntity.handle(dateEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteById(final long id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteById.release(_stmt);
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<DateEntity> getAll() {
    final String _sql = "SELECT * FROM date_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMinute = CursorUtil.getColumnIndexOrThrow(_cursor, "minute");
      final int _cursorIndexOfHour = CursorUtil.getColumnIndexOrThrow(_cursor, "hour");
      final int _cursorIndexOfDay = CursorUtil.getColumnIndexOrThrow(_cursor, "day");
      final int _cursorIndexOfMonth = CursorUtil.getColumnIndexOrThrow(_cursor, "month");
      final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
      final int _cursorIndexOfAdditions = CursorUtil.getColumnIndexOrThrow(_cursor, "additions");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<DateEntity> _result = new ArrayList<DateEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DateEntity _item;
        final int _tmpMinute;
        _tmpMinute = _cursor.getInt(_cursorIndexOfMinute);
        final int _tmpHour;
        _tmpHour = _cursor.getInt(_cursorIndexOfHour);
        final int _tmpDay;
        _tmpDay = _cursor.getInt(_cursorIndexOfDay);
        final int _tmpMonth;
        _tmpMonth = _cursor.getInt(_cursorIndexOfMonth);
        final int _tmpYear;
        _tmpYear = _cursor.getInt(_cursorIndexOfYear);
        final String _tmpAdditions;
        if (_cursor.isNull(_cursorIndexOfAdditions)) {
          _tmpAdditions = null;
        } else {
          _tmpAdditions = _cursor.getString(_cursorIndexOfAdditions);
        }
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item = new DateEntity(_tmpMinute,_tmpHour,_tmpDay,_tmpMonth,_tmpYear,_tmpAdditions,_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public DateEntity getById(final long id) {
    final String _sql = "SELECT * FROM date_table WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMinute = CursorUtil.getColumnIndexOrThrow(_cursor, "minute");
      final int _cursorIndexOfHour = CursorUtil.getColumnIndexOrThrow(_cursor, "hour");
      final int _cursorIndexOfDay = CursorUtil.getColumnIndexOrThrow(_cursor, "day");
      final int _cursorIndexOfMonth = CursorUtil.getColumnIndexOrThrow(_cursor, "month");
      final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
      final int _cursorIndexOfAdditions = CursorUtil.getColumnIndexOrThrow(_cursor, "additions");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final DateEntity _result;
      if(_cursor.moveToFirst()) {
        final int _tmpMinute;
        _tmpMinute = _cursor.getInt(_cursorIndexOfMinute);
        final int _tmpHour;
        _tmpHour = _cursor.getInt(_cursorIndexOfHour);
        final int _tmpDay;
        _tmpDay = _cursor.getInt(_cursorIndexOfDay);
        final int _tmpMonth;
        _tmpMonth = _cursor.getInt(_cursorIndexOfMonth);
        final int _tmpYear;
        _tmpYear = _cursor.getInt(_cursorIndexOfYear);
        final String _tmpAdditions;
        if (_cursor.isNull(_cursorIndexOfAdditions)) {
          _tmpAdditions = null;
        } else {
          _tmpAdditions = _cursor.getString(_cursorIndexOfAdditions);
        }
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result = new DateEntity(_tmpMinute,_tmpHour,_tmpDay,_tmpMonth,_tmpYear,_tmpAdditions,_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
