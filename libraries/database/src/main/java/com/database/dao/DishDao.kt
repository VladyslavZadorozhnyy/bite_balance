package com.database.dao

import androidx.room.*
import com.database.common.Constants
import com.database.entities.DishEntity

@Dao
interface DishDao {
    @Insert
    fun insert(dishEntity: DishEntity): Long
    @Update
    fun updateItem(dishEntity: DishEntity)
    @Query("SELECT * FROM ${Constants.DISH_TABLE_NAME}")
    fun getAll(): List<DishEntity>
    @Query("SELECT * FROM ${Constants.DISH_TABLE_NAME} WHERE id=:id")
    fun getById(id: Long): DishEntity?
    @Query("SELECT * FROM ${Constants.DISH_TABLE_NAME} WHERE name=:name")
    fun getByName(name: String): DishEntity?
    @Query("DELETE FROM ${Constants.DISH_TABLE_NAME} WHERE id=:id")
    fun deleteById(id: Long)
    @Query("DELETE FROM ${Constants.DISH_TABLE_NAME} WHERE name=:name")
    fun deleteByName(name: String)
    @Query("DELETE FROM ${Constants.DISH_TABLE_NAME}")
    fun deleteAll()
}