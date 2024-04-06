package com.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import com.database.common.Constants

@Entity(tableName = Constants.GOAL_METRICS_TABLE_NAME)
data class GoalMetricsEntity(
    @ColumnInfo(name = "table_name") val tableName: String,
    @ColumnInfo(name = "entity_id") val entityId: Long,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
)