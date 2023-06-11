package com.bitebalance.domain.repository

import com.bitebalance.domain.model.NutritionValueModel

interface NutritionValueRepository {
    fun getNutritionValueById(id: Long): NutritionValueModel?
    fun updateNutritionValueById(id: Long, nutritionValueModel: NutritionValueModel)
    fun addNutritionValue(nutritionValueModel: NutritionValueModel): Long
    fun removeNutritionValue(nutritionValueModel: NutritionValueModel)
    fun removeNutritionValueById(id: Long)
    fun getGoalConsumption(): NutritionValueModel?
    fun setGoalConsumption(model: NutritionValueModel)
    fun removeGoalConsumption()
}