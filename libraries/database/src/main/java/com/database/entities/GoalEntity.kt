package com.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import com.database.common.Constants

@Entity(tableName = Constants.GOAL_TABLE_NAME)
data class GoalEntity(
    @ColumnInfo(name = "text_value") val textValue: String,
    @ColumnInfo(name = "active") val active: Boolean,
    @ColumnInfo(name = "achieved") val achieved: Boolean,
    @ColumnInfo(name = "date_created_id") val dateCreatedId: Long,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)