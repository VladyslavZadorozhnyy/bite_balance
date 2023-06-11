package com.bitebalance.presentation.states

import com.bitebalance.domain.model.NutritionValueModel

data class NutritionValueState(
    val nutritionValue: NutritionValueModel? = null,
    val errorMessage: String = "",
    val successMessage: String = "",
)