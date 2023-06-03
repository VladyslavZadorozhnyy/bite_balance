package com.bitebalance.domain.repository

import com.bitebalance.domain.model.NutritionValueModel

interface NutritionValueRepository {
    fun getTodayConsumption(): NutritionValueModel?

    fun getGoalConsumption(): NutritionValueModel?

    fun setGoalConsumption(model: NutritionValueModel)

    fun removeGoalConsumption()
}