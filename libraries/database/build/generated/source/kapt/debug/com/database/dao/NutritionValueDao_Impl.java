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
import com.database.entities.NutritionValueEntity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class NutritionValueDao_Impl implements NutritionValueDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<NutritionValueEntity> __insertionAdapterOfNutritionValueEntity;

  private final EntityDeletionOrUpdateAdapter<NutritionValueEntity> __updateAdapterOfNutritionValueEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public NutritionValueDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNutritionValueEntity = new EntityInsertionAdapter<NutritionValueEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `nutrition_value_table` (`prots`,`fats`,`carbs`,`kcals`,`id`) VALUES (?,?,?,?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NutritionValueEntity value) {
        stmt.bindDouble(1, value.getProts());
        stmt.bindDouble(2, value.getFats());
        stmt.bindDouble(3, value.getCarbs());
        stmt.bindDouble(4, value.getKcals());
        stmt.bindLong(5, value.getId());
      }
    };
    this.__updateAdapterOfNutritionValueEntity = new EntityDeletionOrUpdateAdapter<NutritionValueEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `nutrition_value_table` SET `prots` = ?,`fats` = ?,`carbs` = ?,`kcals` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NutritionValueEntity value) {
        stmt.bindDouble(1, value.getProts());
        stmt.bindDouble(2, value.getFats());
        stmt.bindDouble(3, value.getCarbs());
        stmt.bindDouble(4, value.getKcals());
        stmt.bindLong(5, value.getId());
        stmt.bindLong(6, value.getId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM nutrition_value_table WHERE id=?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM nutrition_value_table";
        return _query;
      }
    };
  }

  @Override
  public long insert(final NutritionValueEntity nutritionValueEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfNutritionValueEntity.insertAndReturnId(nutritionValueEntity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateItem(final NutritionValueEntity nutritionValueEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfNutritionValueEntity.handle(nutritionValueEntity);
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
  public List<NutritionValueEntity> getAll() {
    final String _sql = "SELECT * FROM nutrition_value_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfProts = CursorUtil.getColumnIndexOrThrow(_cursor, "prots");
      final int _cursorIndexOfFats = CursorUtil.getColumnIndexOrThrow(_cursor, "fats");
      final int _cursorIndexOfCarbs = CursorUtil.getColumnIndexOrThrow(_cursor, "carbs");
      final int _cursorIndexOfKcals = CursorUtil.getColumnIndexOrThrow(_cursor, "kcals");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<NutritionValueEntity> _result = new ArrayList<NutritionValueEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final NutritionValueEntity _item;
        final float _tmpProts;
        _tmpProts = _cursor.getFloat(_cursorIndexOfProts);
        final float _tmpFats;
        _tmpFats = _cursor.getFloat(_cursorIndexOfFats);
        final float _tmpCarbs;
        _tmpCarbs = _cursor.getFloat(_cursorIndexOfCarbs);
        final float _tmpKcals;
        _tmpKcals = _cursor.getFloat(_cursorIndexOfKcals);
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item = new NutritionValueEntity(_tmpProts,_tmpFats,_tmpCarbs,_tmpKcals,_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public NutritionValueEntity getById(final long id) {
    final String _sql = "SELECT * FROM nutrition_value_table WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfProts = CursorUtil.getColumnIndexOrThrow(_cursor, "prots");
      final int _cursorIndexOfFats = CursorUtil.getColumnIndexOrThrow(_cursor, "fats");
      final int _cursorIndexOfCarbs = CursorUtil.getColumnIndexOrThrow(_cursor, "carbs");
      final int _cursorIndexOfKcals = CursorUtil.getColumnIndexOrThrow(_cursor, "kcals");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final NutritionValueEntity _result;
      if(_cursor.moveToFirst()) {
        final float _tmpProts;
        _tmpProts = _cursor.getFloat(_cursorIndexOfProts);
        final float _tmpFats;
        _tmpFats = _cursor.getFloat(_cursorIndexOfFats);
        final float _tmpCarbs;
        _tmpCarbs = _cursor.getFloat(_cursorIndexOfCarbs);
        final float _tmpKcals;
        _tmpKcals = _cursor.getFloat(_cursorIndexOfKcals);
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result = new NutritionValueEntity(_tmpProts,_tmpFats,_tmpCarbs,_tmpKcals,_tmpId);
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
