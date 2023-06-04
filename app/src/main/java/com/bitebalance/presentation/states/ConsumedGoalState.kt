package com.bitebalance.presentation.states

import com.bitebalance.domain.model.NutritionValueModel

data class ConsumptionState(
    val consumptionGoalPair: Pair<NutritionValueModel, NutritionValueModel>,
    val isLoading: Boolean = false,
)