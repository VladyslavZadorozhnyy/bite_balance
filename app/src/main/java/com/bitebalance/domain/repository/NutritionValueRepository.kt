package com.bitebalance.domain.repository

import com.bitebalance.domain.model.NutritionValueModel

interface NutritionValueRepository {
    fun getNutritionValueById(id: Long): NutritionValueModel?
    fun addNutritionValue(nutritionValueModel: NutritionValueModel): Long
    fun getGoalConsumption(): NutritionValueModel?

    fun setGoalConsumption(model: NutritionValueModel)

    fun removeGoalConsumption()
}