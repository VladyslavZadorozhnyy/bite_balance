package com.bitebalance.common

import com.ui.model.NutritionValueModel

object Constants {
    const val BREAKFAST_HOUR = 12
    const val LUNCH_HOUR = 19

    private const val ST_PROTS_CONSUMPTION = 105F
    private const val ST_FATS_CONSUMPTION = 56F
    private const val ST_CARBS_CONSUMPTION = 170F
    private const val ST_KCALS_CONSUMPTION = 2400F

    val DEFAULT_GOAL_CONSUMPTION = NutritionValueModel(
        prots = ST_PROTS_CONSUMPTION,
        fats = ST_FATS_CONSUMPTION,
        carbs = ST_CARBS_CONSUMPTION,
        kcals = ST_KCALS_CONSUMPTION,
    )

    const val COMPONENT_VAL_MIN = 0
    const val COMPONENT_VAL_MAX = 10000
}