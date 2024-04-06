package com.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import com.database.common.Constants

@Entity(tableName = Constants.NUTRITION_VALUE_TABLE_NAME)
data class NutritionValueEntity(
    @ColumnInfo(name = "prots") val prots: Float,
    @ColumnInfo(name = "fats") val fats: Float,
    @ColumnInfo(name = "carbs") val carbs: Float,
    @ColumnInfo(name = "kcals") val kcals: Float,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)