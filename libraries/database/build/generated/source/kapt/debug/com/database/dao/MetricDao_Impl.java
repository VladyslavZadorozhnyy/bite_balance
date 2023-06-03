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
import com.database.entities.MetricEntity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MetricDao_Impl implements MetricDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MetricEntity> __insertionAdapterOfMetricEntity;

  private final EntityDeletionOrUpdateAdapter<MetricEntity> __updateAdapterOfMetricEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public MetricDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMetricEntity = new EntityInsertionAdapter<MetricEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `metric_table` (`name`,`hint`,`suffix`,`editable`,`id`) VALUES (?,?,?,?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MetricEntity value) {
        if (value.getName() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getName());
        }
        if (value.getHint() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getHint());
        }
        if (value.getSuffix() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSuffix());
        }
        final int _tmp = value.getEditable() ? 1 : 0;
        stmt.bindLong(4, _tmp);
        stmt.bindLong(5, value.getId());
      }
    };
    this.__updateAdapterOfMetricEntity = new EntityDeletionOrUpdateAdapter<MetricEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `metric_table` SET `name` = ?,`hint` = ?,`suffix` = ?,`editable` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MetricEntity value) {
        if (value.getName() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getName());
        }
        if (value.getHint() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getHint());
        }
        if (value.getSuffix() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSuffix());
        }
        final int _tmp = value.getEditable() ? 1 : 0;
        stmt.bindLong(4, _tmp);
        stmt.bindLong(5, value.getId());
        stmt.bindLong(6, value.getId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM metric_table WHERE id=?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM metric_table";
        return _query;
      }
    };
  }

  @Override
  public long insert(final MetricEntity metricEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfMetricEntity.insertAndReturnId(metricEntity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateItem(final MetricEntity metricEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfMetricEntity.handle(metricEntity);
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
  public List<MetricEntity> getAll() {
    final String _sql = "SELECT * FROM metric_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfHint = CursorUtil.getColumnIndexOrThrow(_cursor, "hint");
      final int _cursorIndexOfSuffix = CursorUtil.getColumnIndexOrThrow(_cursor, "suffix");
      final int _cursorIndexOfEditable = CursorUtil.getColumnIndexOrThrow(_cursor, "editable");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<MetricEntity> _result = new ArrayList<MetricEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final MetricEntity _item;
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpHint;
        if (_cursor.isNull(_cursorIndexOfHint)) {
          _tmpHint = null;
        } else {
          _tmpHint = _cursor.getString(_cursorIndexOfHint);
        }
        final String _tmpSuffix;
        if (_cursor.isNull(_cursorIndexOfSuffix)) {
          _tmpSuffix = null;
        } else {
          _tmpSuffix = _cursor.getString(_cursorIndexOfSuffix);
        }
        final boolean _tmpEditable;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfEditable);
        _tmpEditable = _tmp != 0;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item = new MetricEntity(_tmpName,_tmpHint,_tmpSuffix,_tmpEditable,_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public MetricEntity getById(final long id) {
    final String _sql = "SELECT * FROM metric_table WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfHint = CursorUtil.getColumnIndexOrThrow(_cursor, "hint");
      final int _cursorIndexOfSuffix = CursorUtil.getColumnIndexOrThrow(_cursor, "suffix");
      final int _cursorIndexOfEditable = CursorUtil.getColumnIndexOrThrow(_cursor, "editable");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final MetricEntity _result;
      if(_cursor.moveToFirst()) {
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpHint;
        if (_cursor.isNull(_cursorIndexOfHint)) {
          _tmpHint = null;
        } else {
          _tmpHint = _cursor.getString(_cursorIndexOfHint);
        }
        final String _tmpSuffix;
        if (_cursor.isNull(_cursorIndexOfSuffix)) {
          _tmpSuffix = null;
        } else {
          _tmpSuffix = _cursor.getString(_cursorIndexOfSuffix);
        }
        final boolean _tmpEditable;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfEditable);
        _tmpEditable = _tmp != 0;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result = new MetricEntity(_tmpName,_tmpHint,_tmpSuffix,_tmpEditable,_tmpId);
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
