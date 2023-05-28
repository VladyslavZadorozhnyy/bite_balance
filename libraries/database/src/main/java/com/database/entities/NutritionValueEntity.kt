package com.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.database.common.Constants

@Entity(tableName = Constants.NUTRITION_VALUE_TABLE_NAME)
data class NutritionValueEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "prots") val prots: Float,
    @ColumnInfo(name = "fats") val fats: Float,
    @ColumnInfo(name = "carbs") val carbs: Float,
    @ColumnInfo(name = "kcals") val kcals: Float
)