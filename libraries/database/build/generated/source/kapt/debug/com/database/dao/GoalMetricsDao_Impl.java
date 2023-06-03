package com.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.database.entities.GoalMetricsEntity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class GoalMetricsDao_Impl implements GoalMetricsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<GoalMetricsEntity> __insertionAdapterOfGoalMetricsEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateItem;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByTableName;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public GoalMetricsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGoalMetricsEntity = new EntityInsertionAdapter<GoalMetricsEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `goal_metrics_table` (`table_name`,`entity_id`,`id`) VALUES (?,?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GoalMetricsEntity value) {
        if (value.getTableName() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTableName());
        }
        stmt.bindLong(2, value.getEntityId());
        stmt.bindLong(3, value.getId());
      }
    };
    this.__preparedStmtOfUpdateItem = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE goal_metrics_table SET entity_id=? WHERE table_name=?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM goal_metrics_table WHERE id=?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteByTableName = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM goal_metrics_table WHERE table_name=?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM goal_metrics_table";
        return _query;
      }
    };
  }

  @Override
  public long insert(final GoalMetricsEntity goalMetricsEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfGoalMetricsEntity.insertAndReturnId(goalMetricsEntity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateItem(final long newEntityId, final String tableName) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateItem.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, newEntityId);
    _argIndex = 2;
    if (tableName == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, tableName);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateItem.release(_stmt);
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
  public void deleteByTableName(final String tableName) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByTableName.acquire();
    int _argIndex = 1;
    if (tableName == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, tableName);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteByTableName.release(_stmt);
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
  public List<GoalMetricsEntity> getAll() {
    final String _sql = "SELECT * FROM goal_metrics_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTableName = CursorUtil.getColumnIndexOrThrow(_cursor, "table_name");
      final int _cursorIndexOfEntityId = CursorUtil.getColumnIndexOrThrow(_cursor, "entity_id");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<GoalMetricsEntity> _result = new ArrayList<GoalMetricsEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final GoalMetricsEntity _item;
        final String _tmpTableName;
        if (_cursor.isNull(_cursorIndexOfTableName)) {
          _tmpTableName = null;
        } else {
          _tmpTableName = _cursor.getString(_cursorIndexOfTableName);
        }
        final long _tmpEntityId;
        _tmpEntityId = _cursor.getLong(_cursorIndexOfEntityId);
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item = new GoalMetricsEntity(_tmpTableName,_tmpEntityId,_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public GoalMetricsEntity getByTableName(final String tableName) {
    final String _sql = "SELECT * FROM goal_metrics_table WHERE table_name=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (tableName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, tableName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTableName = CursorUtil.getColumnIndexOrThrow(_cursor, "table_name");
      final int _cursorIndexOfEntityId = CursorUtil.getColumnIndexOrThrow(_cursor, "entity_id");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final GoalMetricsEntity _result;
      if(_cursor.moveToFirst()) {
        final String _tmpTableName;
        if (_cursor.isNull(_cursorIndexOfTableName)) {
          _tmpTableName = null;
        } else {
          _tmpTableName = _cursor.getString(_cursorIndexOfTableName);
        }
        final long _tmpEntityId;
        _tmpEntityId = _cursor.getLong(_cursorIndexOfEntityId);
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result = new GoalMetricsEntity(_tmpTableName,_tmpEntityId,_tmpId);
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
