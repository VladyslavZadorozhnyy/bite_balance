package com.database.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.database.common.Constants
import com.database.dao.*
import com.database.entities.*

@Database(entities = [
    DateEntity::class,
    DishEntity::class,
    GoalEntity::class,
    IconLegendEntity::class,
    MealEntity::class,
    MetricEntity::class,
    NutritionValueEntity::class,
    SettingEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dateDao(): DateDao

    abstract fun dishDao(): DishDao

    abstract fun goalDao(): GoalDao

    abstract fun iconLegendDao(): IconLegendDao

    abstract fun mealDao(): MealDao

    abstract fun metricDao(): MetricDao

    abstract fun nutritionValueDao(): NutritionValueDao

    abstract fun settingDao(): SettingDao
}