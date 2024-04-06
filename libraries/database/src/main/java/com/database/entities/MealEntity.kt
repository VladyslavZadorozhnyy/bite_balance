package com.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import com.database.common.Constants

@Entity(tableName = Constants.MEAL_TABLE_NAME)
data class MealEntity(
    @ColumnInfo(name = "meal_time_id") val mealTimeId: Long,
    @ColumnInfo(name = "dish_id") val dishId: Long,
    @ColumnInfo(name = "amount") val amount: Float,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)