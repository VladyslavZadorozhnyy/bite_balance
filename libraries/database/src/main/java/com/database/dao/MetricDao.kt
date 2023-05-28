package com.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.database.common.Constants
import com.database.entities.MetricEntity

@Dao
interface MetricDao {
    @Insert
    fun insert(metricEntity: MetricEntity)

    @Update
    fun updateItem(metricEntity: MetricEntity)

    @Query("SELECT * FROM ${Constants.METRIC_TABLE_NAME}")
    fun getAll(): List<MetricEntity>

    @Query("SELECT * FROM ${Constants.METRIC_TABLE_NAME} WHERE id=:id")
    fun getById(id: Int): MetricEntity?

    @Query("DELETE FROM ${Constants.METRIC_TABLE_NAME} WHERE id=:id")
    fun deleteById(id: Int)

    @Query("DELETE FROM ${Constants.METRIC_TABLE_NAME}")
    fun deleteAll()
}