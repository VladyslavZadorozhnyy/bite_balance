package com.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.database.common.Constants

@Entity(tableName = Constants.ICON_LEGEND_TABLE_NAME)
data class IconLegendEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "icon_res") val iconRes: Int,
    @ColumnInfo(name = "instruction_text") val instructionText: String
)