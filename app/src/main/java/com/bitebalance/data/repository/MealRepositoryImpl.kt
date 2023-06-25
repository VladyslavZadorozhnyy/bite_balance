package com.bitebalance.data.repository

import com.bitebalance.domain.model.MealModel
import com.bitebalance.domain.model.fromEntity
import com.bitebalance.domain.model.toEntity
import com.bitebalance.domain.repository.MealRepository
import com.database.db.AppDaoDatabase

class MealRepositoryImpl(
    private val appDaoDatabase: AppDaoDatabase,
) : MealRepository {
    override fun addMeal(mealModel: MealModel) {
        appDaoDatabase.getMealDao().insert(mealModel.toEntity())
    }

    override fun removeAllMeals() {
        appDaoDatabase.getMealDao().deleteAll()
    }

    override fun removeMealById(id: Long) {
        appDaoDatabase.getMealDao().deleteById(id)
    }

    override fun getAllMeals(): List<MealModel> {
        return appDaoDatabase
            .getMealDao()
            .getAll()
            .map { MealModel.Companion.fromEntity(it) }
    }

    override fun getMealsByDate(dateModelId: Int): List<MealModel> {
        return appDaoDatabase
            .getMealDao()
            .getByMealTime(dateModelId)
            .map { MealModel.Companion.fromEntity(it) }
    }
}