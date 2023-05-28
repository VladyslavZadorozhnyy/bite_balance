package com.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.database.common.Constants
import com.database.entities.DishEntity

@Dao
interface DishDao {
    @Insert
    fun insert(dishEntity: DishEntity)

    @Update
    fun updateItem(dishEntity: DishEntity)

    @Query("SELECT * FROM ${Constants.DISH_TABLE_NAME}")
    fun getAll(): List<DishEntity>

    @Query("SELECT * FROM ${Constants.DISH_TABLE_NAME} WHERE id=:id")
    fun getById(id: Int): DishEntity?

    @Query("DELETE FROM ${Constants.DISH_TABLE_NAME} WHERE id=:id")
    fun deleteById(id: Int)

    @Query("DELETE FROM ${Constants.DISH_TABLE_NAME}")
    fun deleteAll()
}