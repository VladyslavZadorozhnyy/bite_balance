package com.bitebalance.domain.repository

import com.bitebalance.domain.model.MealModel

interface MealRepository {
    fun addMeal(mealModel: MealModel)
    fun removeAllMeals()
    fun getAllMeals(): List<MealModel>
    fun getMealsByDate(dateModelId: Int): List<MealModel>
}