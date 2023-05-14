package com.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "icon_legend_table")
data class IconLegendEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "icon_res") val iconRes: Int?,
    @ColumnInfo(name = "instruction_text") val instructionText: String?
)