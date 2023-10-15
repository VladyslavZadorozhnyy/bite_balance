package com.bitebalance.presentation.states

import com.ui.model.NutritionValueModel

data class StatsState(
    val monthNutrition: List<NutritionValueModel>? = null,
    val goalConsumption: NutritionValueModel? = null,
    val message: String = "",
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = true
)