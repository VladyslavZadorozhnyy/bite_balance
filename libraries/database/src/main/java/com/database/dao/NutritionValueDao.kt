package com.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.database.common.Constants
import com.database.entities.NutritionValueEntity

@Dao
interface NutritionValueDao {
    @Insert
    fun insert(nutritionValueEntity: NutritionValueEntity)

    @Update
    fun updateItem(nutritionValueEntity: NutritionValueEntity)

    @Query("SELECT * FROM ${Constants.NUTRITION_VALUE_TABLE_NAME}")
    fun getAll(): List<NutritionValueEntity>

    @Query("SELECT * FROM ${Constants.NUTRITION_VALUE_TABLE_NAME} WHERE id=:id")
    fun getById(id: Int): NutritionValueEntity?

    @Query("DELETE FROM ${Constants.NUTRITION_VALUE_TABLE_NAME} WHERE id=:id")
    fun deleteById(id: Int)

    @Query("DELETE FROM ${Constants.NUTRITION_VALUE_TABLE_NAME}")
    fun deleteAll()
}