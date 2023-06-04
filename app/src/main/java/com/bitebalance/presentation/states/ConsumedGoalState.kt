package com.bitebalance.presentation.states

import com.bitebalance.domain.model.NutritionValueModel

data class ConsumedGoalState(
    val consumedGoalPair: Pair<NutritionValueModel, NutritionValueModel>,
    val isLoading: Boolean = false,
)