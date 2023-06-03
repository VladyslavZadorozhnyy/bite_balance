package com.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.database.common.Constants

@Entity(tableName = Constants.DISH_TABLE_NAME)
data class DishEntity(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "icon_res") val iconRes: Int,
    @ColumnInfo(name = "nutrition_model_id") val nutritionModelId: Long,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)