package com.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.database.common.Constants
import com.database.entities.IconLegendEntity

@Dao
interface IconLegendDao {
    @Insert
    fun insert(iconLegendEntity: IconLegendEntity)

    @Update
    fun updateItem(iconLegendEntity: IconLegendEntity)

    @Query("SELECT * FROM ${Constants.ICON_LEGEND_TABLE_NAME}")
    fun getAll(): List<IconLegendEntity>

    @Query("SELECT * FROM ${Constants.ICON_LEGEND_TABLE_NAME} WHERE id=:id")
    fun getById(id: Int): IconLegendEntity?

    @Query("DELETE FROM ${Constants.ICON_LEGEND_TABLE_NAME} WHERE id=:id")
    fun deleteById(id: Int)

    @Query("DELETE FROM ${Constants.ICON_LEGEND_TABLE_NAME}")
    fun deleteAll()
}