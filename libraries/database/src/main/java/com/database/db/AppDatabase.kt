package com.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.database.dao.IconLegendDao
import com.database.entities.IconLegendEntity

@Database(entities = [IconLegendEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun iconLegendDao(): IconLegendDao
}