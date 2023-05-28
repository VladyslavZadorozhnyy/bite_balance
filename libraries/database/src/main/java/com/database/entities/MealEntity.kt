package com.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.database.common.Constants

@Entity(tableName = Constants.MEAL_TABLE_NAME)
data class MealEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "meal_time_id") val mealTimeId: Int,
    @ColumnInfo(name = "amount") val amount: Float,
    @ColumnInfo(name = "nutrition_value_id") val nutritionValueId: Int
)