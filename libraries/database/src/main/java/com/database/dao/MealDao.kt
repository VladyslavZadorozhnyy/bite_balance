package com.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.database.common.Constants
import com.database.entities.MealEntity

@Dao
interface MealDao {
    @Insert
    fun insert(mealEntity: MealEntity)

    @Update
    fun updateItem(mealEntity: MealEntity)

    @Query("SELECT * FROM ${Constants.MEAL_TABLE_NAME}")
    fun getAll(): List<MealEntity>

    @Query("SELECT * FROM ${Constants.MEAL_TABLE_NAME} WHERE id=:id")
    fun getById(id: Int): MealEntity?

    @Query("DELETE FROM ${Constants.MEAL_TABLE_NAME} WHERE id=:id")
    fun deleteById(id: Int)

    @Query("DELETE FROM ${Constants.MEAL_TABLE_NAME}")
    fun deleteAll()
}