package com.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.database.common.Constants
import com.database.entities.GoalMetricsEntity

@Dao
interface GoalMetricsDao {
    @Insert
    fun insert(goalMetricsEntity: GoalMetricsEntity): Long

    @Query("UPDATE ${Constants.GOAL_METRICS_TABLE_NAME} SET entity_id=:newEntityId WHERE table_name=:tableName")
    fun updateItem(newEntityId: Long, tableName: String)

    @Query("SELECT * FROM ${Constants.GOAL_METRICS_TABLE_NAME}")
    fun getAll(): List<GoalMetricsEntity>

    @Query("SELECT * FROM ${Constants.GOAL_METRICS_TABLE_NAME} WHERE table_name=:tableName")
    fun getByTableName(tableName: String): GoalMetricsEntity?

    @Query("DELETE FROM ${Constants.GOAL_METRICS_TABLE_NAME} WHERE id=:id")
    fun deleteById(id: Long)

    @Query("DELETE FROM ${Constants.GOAL_METRICS_TABLE_NAME} WHERE table_name=:tableName")
    fun deleteByTableName(tableName: String)

    @Query("DELETE FROM ${Constants.GOAL_METRICS_TABLE_NAME}")
    fun deleteAll()
}