package com.database.dao

import androidx.room.*
import com.database.common.Constants
import com.database.entities.GoalEntity

@Dao
interface GoalDao {
    @Insert
    fun insert(goalEntity: GoalEntity): Long
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateItem(goalEntity: GoalEntity)
    @Query("SELECT * FROM ${Constants.GOAL_TABLE_NAME}")
    fun getAll(): List<GoalEntity>
    @Query("SELECT * FROM ${Constants.GOAL_TABLE_NAME} WHERE id=:id")
    fun getById(id: Long): GoalEntity?
    @Query("DELETE FROM ${Constants.GOAL_TABLE_NAME} WHERE id=:id")
    fun deleteById(id: Long)
    @Query("DELETE FROM ${Constants.GOAL_TABLE_NAME}")
    fun deleteAll()
}