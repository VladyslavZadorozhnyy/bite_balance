package com.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.database.common.Constants
import com.database.entities.SettingEntity

@Dao
interface SettingDao {
    @Insert
    fun insert(settingEntity: SettingEntity)

    @Update
    fun updateItem(settingEntity: SettingEntity)

    @Query("SELECT * FROM ${Constants.SETTING_TABLE_NAME}")
    fun getAll(): List<SettingEntity>

    @Query("SELECT * FROM ${Constants.SETTING_TABLE_NAME} WHERE id=:id")
    fun getById(id: Int): SettingEntity?

    @Query("DELETE FROM ${Constants.SETTING_TABLE_NAME} WHERE id=:id")
    fun deleteById(id: Int)

    @Query("DELETE FROM ${Constants.SETTING_TABLE_NAME}")
    fun deleteAll()
}