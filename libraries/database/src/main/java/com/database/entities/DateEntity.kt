package com.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.database.common.Constants

@Entity(tableName = Constants.DATE_TABLE_NAME)
data class DateEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "minute") val minute: Int,
    @ColumnInfo(name = "hour") val hour: Int,
    @ColumnInfo(name = "day") val day: Int,
    @ColumnInfo(name = "month") val month: Int,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "additions") val additions: String
)