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
import com.database.entities.MealEntity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MealDao_Impl implements MealDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MealEntity> __insertionAdapterOfMealEntity;

  private final EntityDeletionOrUpdateAdapter<MealEntity> __updateAdapterOfMealEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public MealDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMealEntity = new EntityInsertionAdapter<MealEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `meal_table` (`meal_time_id`,`amount`,`nutrition_value_id`,`id`) VALUES (?,?,?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MealEntity value) {
        stmt.bindLong(1, value.getMealTimeId());
        stmt.bindDouble(2, value.getAmount());
        stmt.bindLong(3, value.getNutritionValueId());
        stmt.bindLong(4, value.getId());
      }
    };
    this.__updateAdapterOfMealEntity = new EntityDeletionOrUpdateAdapter<MealEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `meal_table` SET `meal_time_id` = ?,`amount` = ?,`nutrition_value_id` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MealEntity value) {
        stmt.bindLong(1, value.getMealTimeId());
        stmt.bindDouble(2, value.getAmount());
        stmt.bindLong(3, value.getNutritionValueId());
        stmt.bindLong(4, value.getId());
        stmt.bindLong(5, value.getId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM meal_table WHERE id=?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM meal_table";
        return _query;
      }
    };
  }

  @Override
  public long insert(final MealEntity mealEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfMealEntity.insertAndReturnId(mealEntity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateItem(final MealEntity mealEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfMealEntity.handle(mealEntity);
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
  public List<MealEntity> getAll() {
    final String _sql = "SELECT * FROM meal_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMealTimeId = CursorUtil.getColumnIndexOrThrow(_cursor, "meal_time_id");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final int _cursorIndexOfNutritionValueId = CursorUtil.getColumnIndexOrThrow(_cursor, "nutrition_value_id");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<MealEntity> _result = new ArrayList<MealEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final MealEntity _item;
        final long _tmpMealTimeId;
        _tmpMealTimeId = _cursor.getLong(_cursorIndexOfMealTimeId);
        final float _tmpAmount;
        _tmpAmount = _cursor.getFloat(_cursorIndexOfAmount);
        final long _tmpNutritionValueId;
        _tmpNutritionValueId = _cursor.getLong(_cursorIndexOfNutritionValueId);
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item = new MealEntity(_tmpMealTimeId,_tmpAmount,_tmpNutritionValueId,_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public MealEntity getById(final long id) {
    final String _sql = "SELECT * FROM meal_table WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfMealTimeId = CursorUtil.getColumnIndexOrThrow(_cursor, "meal_time_id");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final int _cursorIndexOfNutritionValueId = CursorUtil.getColumnIndexOrThrow(_cursor, "nutrition_value_id");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final MealEntity _result;
      if(_cursor.moveToFirst()) {
        final long _tmpMealTimeId;
        _tmpMealTimeId = _cursor.getLong(_cursorIndexOfMealTimeId);
        final float _tmpAmount;
        _tmpAmount = _cursor.getFloat(_cursorIndexOfAmount);
        final long _tmpNutritionValueId;
        _tmpNutritionValueId = _cursor.getLong(_cursorIndexOfNutritionValueId);
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result = new MealEntity(_tmpMealTimeId,_tmpAmount,_tmpNutritionValueId,_tmpId);
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
