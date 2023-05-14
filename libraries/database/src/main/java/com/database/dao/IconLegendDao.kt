package com.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.database.entities.IconLegendEntity

@Dao
interface IconLegendDao {
    @Query("SELECT * FROM icon_legend_table")
    fun getAll(): List<IconLegendEntity>

    @Insert
    fun insert(iconLegendEntity: IconLegendEntity)

    @Delete
    fun delete(iconLegendEntity: IconLegendEntity)
}