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
import com.database.entities.IconLegendEntity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class IconLegendDao_Impl implements IconLegendDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<IconLegendEntity> __insertionAdapterOfIconLegendEntity;

  private final EntityDeletionOrUpdateAdapter<IconLegendEntity> __updateAdapterOfIconLegendEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public IconLegendDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfIconLegendEntity = new EntityInsertionAdapter<IconLegendEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `icon_legend_table` (`icon_res`,`instruction_text`,`id`) VALUES (?,?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, IconLegendEntity value) {
        stmt.bindLong(1, value.getIconRes());
        if (value.getInstructionText() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getInstructionText());
        }
        stmt.bindLong(3, value.getId());
      }
    };
    this.__updateAdapterOfIconLegendEntity = new EntityDeletionOrUpdateAdapter<IconLegendEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `icon_legend_table` SET `icon_res` = ?,`instruction_text` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, IconLegendEntity value) {
        stmt.bindLong(1, value.getIconRes());
        if (value.getInstructionText() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getInstructionText());
        }
        stmt.bindLong(3, value.getId());
        stmt.bindLong(4, value.getId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM icon_legend_table WHERE id=?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM icon_legend_table";
        return _query;
      }
    };
  }

  @Override
  public long insert(final IconLegendEntity iconLegendEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfIconLegendEntity.insertAndReturnId(iconLegendEntity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateItem(final IconLegendEntity iconLegendEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfIconLegendEntity.handle(iconLegendEntity);
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
  public List<IconLegendEntity> getAll() {
    final String _sql = "SELECT * FROM icon_legend_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIconRes = CursorUtil.getColumnIndexOrThrow(_cursor, "icon_res");
      final int _cursorIndexOfInstructionText = CursorUtil.getColumnIndexOrThrow(_cursor, "instruction_text");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<IconLegendEntity> _result = new ArrayList<IconLegendEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final IconLegendEntity _item;
        final int _tmpIconRes;
        _tmpIconRes = _cursor.getInt(_cursorIndexOfIconRes);
        final String _tmpInstructionText;
        if (_cursor.isNull(_cursorIndexOfInstructionText)) {
          _tmpInstructionText = null;
        } else {
          _tmpInstructionText = _cursor.getString(_cursorIndexOfInstructionText);
        }
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item = new IconLegendEntity(_tmpIconRes,_tmpInstructionText,_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public IconLegendEntity getById(final long id) {
    final String _sql = "SELECT * FROM icon_legend_table WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIconRes = CursorUtil.getColumnIndexOrThrow(_cursor, "icon_res");
      final int _cursorIndexOfInstructionText = CursorUtil.getColumnIndexOrThrow(_cursor, "instruction_text");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final IconLegendEntity _result;
      if(_cursor.moveToFirst()) {
        final int _tmpIconRes;
        _tmpIconRes = _cursor.getInt(_cursorIndexOfIconRes);
        final String _tmpInstructionText;
        if (_cursor.isNull(_cursorIndexOfInstructionText)) {
          _tmpInstructionText = null;
        } else {
          _tmpInstructionText = _cursor.getString(_cursorIndexOfInstructionText);
        }
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result = new IconLegendEntity(_tmpIconRes,_tmpInstructionText,_tmpId);
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
