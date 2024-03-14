package com.database.db

import com.database.dao.*
import androidx.room.Database
import com.database.entities.*
import androidx.room.RoomDatabase

@Database(entities = [
    DateEntity::class,
    DishEntity::class,
    GoalEntity::class,
    GoalMetricsEntity::class,
    IconLegendEntity::class,
    MealEntity::class,
    MetricEntity::class,
    NutritionValueEntity::class,
    SettingEntity::class],
    exportSchema = false,
    version = 6)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dateDao(): DateDao

    abstract fun dishDao(): DishDao

    abstract fun goalDao(): GoalDao

    abstract fun goalMetricsDao(): GoalMetricsDao

    abstract fun iconLegendDao(): IconLegendDao

    abstract fun mealDao(): MealDao

    abstract fun metricDao(): MetricDao

    abstract fun nutritionValueDao(): NutritionValueDao

    abstract fun settingDao(): SettingDao
}