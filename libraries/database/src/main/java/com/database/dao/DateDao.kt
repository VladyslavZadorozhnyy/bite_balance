package com.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.database.common.Constants
import com.database.entities.DateEntity

@Dao
interface DateDao {
    @Insert
    fun insert(dateEntity: DateEntity)

    @Update
    fun updateItem(dateEntity: DateEntity)

    @Query("SELECT * FROM ${Constants.DATE_TABLE_NAME}")
    fun getAll(): List<DateEntity>

    @Query("SELECT * FROM ${Constants.DATE_TABLE_NAME} WHERE id=:id")
    fun getById(id: Int): DateEntity?

    @Query("DELETE FROM ${Constants.DATE_TABLE_NAME} WHERE id=:id")
    fun deleteById(id: Int)

    @Query("DELETE FROM ${Constants.DATE_TABLE_NAME}")
    fun deleteAll()
}