package com.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import com.database.common.Constants

@Entity(tableName = Constants.METRIC_TABLE_NAME)
data class MetricEntity(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "hint") val hint: String,
    @ColumnInfo(name = "suffix") val suffix: String,
    @ColumnInfo(name = "editable") val editable: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)