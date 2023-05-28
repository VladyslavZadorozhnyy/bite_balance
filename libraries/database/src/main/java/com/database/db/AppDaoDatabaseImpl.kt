package com.database.db

import android.content.Context
import androidx.room.Room
import com.database.common.Constants
import com.database.dao.*

class AppDaoDatabaseImpl(
    private val context: Context
) : AppDaoDatabase {
    private var instance: AppDatabase? = null

    private fun getInstance(): AppDatabase {
        synchronized(AppDatabase::class.java) {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    Constants.DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
            }
        }
        return instance!!
    }

    override fun getDateDao(): DateDao {
        return getInstance().dateDao()
    }

    override fun getDishDao(): DishDao {
        return getInstance().dishDao()
    }

    override fun getGoalDao(): GoalDao {
        return getInstance().goalDao()
    }

    override fun getIconLegendDao(): IconLegendDao {
        return getInstance().iconLegendDao()
    }

    override fun getMealDao(): MealDao {
        return getInstance().mealDao()
    }

    override fun getMetricDao(): MetricDao {
        return getInstance().metricDao()
    }

    override fun getNutritionValueDao(): NutritionValueDao {
        return getInstance().nutritionValueDao()
    }

    override fun getSettingDao(): SettingDao {
        return getInstance().settingDao()
    }
}