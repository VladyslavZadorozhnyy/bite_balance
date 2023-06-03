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
import com.database.entities.GoalEntity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class GoalDao_Impl implements GoalDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<GoalEntity> __insertionAdapterOfGoalEntity;

  private final EntityDeletionOrUpdateAdapter<GoalEntity> __updateAdapterOfGoalEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public GoalDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGoalEntity = new EntityInsertionAdapter<GoalEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `goal_table` (`text_value`,`active`,`achieved`,`date_created_id`,`id`) VALUES (?,?,?,?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GoalEntity value) {
        if (value.getTextValue() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTextValue());
        }
        final int _tmp = value.getActive() ? 1 : 0;
        stmt.bindLong(2, _tmp);
        final int _tmp_1 = value.getAchieved() ? 1 : 0;
        stmt.bindLong(3, _tmp_1);
        stmt.bindLong(4, value.getDateCreatedId());
        stmt.bindLong(5, value.getId());
      }
    };
    this.__updateAdapterOfGoalEntity = new EntityDeletionOrUpdateAdapter<GoalEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `goal_table` SET `text_value` = ?,`active` = ?,`achieved` = ?,`date_created_id` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GoalEntity value) {
        if (value.getTextValue() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTextValue());
        }
        final int _tmp = value.getActive() ? 1 : 0;
        stmt.bindLong(2, _tmp);
        final int _tmp_1 = value.getAchieved() ? 1 : 0;
        stmt.bindLong(3, _tmp_1);
        stmt.bindLong(4, value.getDateCreatedId());
        stmt.bindLong(5, value.getId());
        stmt.bindLong(6, value.getId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM goal_table WHERE id=?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM goal_table";
        return _query;
      }
    };
  }

  @Override
  public long insert(final GoalEntity goalEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfGoalEntity.insertAndReturnId(goalEntity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateItem(final GoalEntity goalEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfGoalEntity.handle(goalEntity);
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
  public List<GoalEntity> getAll() {
    final String _sql = "SELECT * FROM goal_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTextValue = CursorUtil.getColumnIndexOrThrow(_cursor, "text_value");
      final int _cursorIndexOfActive = CursorUtil.getColumnIndexOrThrow(_cursor, "active");
      final int _cursorIndexOfAchieved = CursorUtil.getColumnIndexOrThrow(_cursor, "achieved");
      final int _cursorIndexOfDateCreatedId = CursorUtil.getColumnIndexOrThrow(_cursor, "date_created_id");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<GoalEntity> _result = new ArrayList<GoalEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final GoalEntity _item;
        final String _tmpTextValue;
        if (_cursor.isNull(_cursorIndexOfTextValue)) {
          _tmpTextValue = null;
        } else {
          _tmpTextValue = _cursor.getString(_cursorIndexOfTextValue);
        }
        final boolean _tmpActive;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfActive);
        _tmpActive = _tmp != 0;
        final boolean _tmpAchieved;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfAchieved);
        _tmpAchieved = _tmp_1 != 0;
        final long _tmpDateCreatedId;
        _tmpDateCreatedId = _cursor.getLong(_cursorIndexOfDateCreatedId);
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item = new GoalEntity(_tmpTextValue,_tmpActive,_tmpAchieved,_tmpDateCreatedId,_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public GoalEntity getById(final long id) {
    final String _sql = "SELECT * FROM goal_table WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTextValue = CursorUtil.getColumnIndexOrThrow(_cursor, "text_value");
      final int _cursorIndexOfActive = CursorUtil.getColumnIndexOrThrow(_cursor, "active");
      final int _cursorIndexOfAchieved = CursorUtil.getColumnIndexOrThrow(_cursor, "achieved");
      final int _cursorIndexOfDateCreatedId = CursorUtil.getColumnIndexOrThrow(_cursor, "date_created_id");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final GoalEntity _result;
      if(_cursor.moveToFirst()) {
        final String _tmpTextValue;
        if (_cursor.isNull(_cursorIndexOfTextValue)) {
          _tmpTextValue = null;
        } else {
          _tmpTextValue = _cursor.getString(_cursorIndexOfTextValue);
        }
        final boolean _tmpActive;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfActive);
        _tmpActive = _tmp != 0;
        final boolean _tmpAchieved;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfAchieved);
        _tmpAchieved = _tmp_1 != 0;
        final long _tmpDateCreatedId;
        _tmpDateCreatedId = _cursor.getLong(_cursorIndexOfDateCreatedId);
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result = new GoalEntity(_tmpTextValue,_tmpActive,_tmpAchieved,_tmpDateCreatedId,_tmpId);
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
