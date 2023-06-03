package com.database.db

import com.database.dao.*

interface AppDaoDatabase {

    fun getDateDao(): DateDao

    fun getDishDao(): DishDao

    fun getGoalDao(): GoalDao

    fun getGoalMetricsDao(): GoalMetricsDao

    fun getIconLegendDao(): IconLegendDao

    fun getMealDao(): MealDao

    fun getMetricDao(): MetricDao

    fun getNutritionValueDao(): NutritionValueDao

    fun getSettingDao(): SettingDao
}