package com.bitebalance.common

import com.ui.model.NutritionValueModel

object Constants {
    const val BREAKFAST_HOUR = 12
    const val LUNCH_HOUR = 19

    private const val ST_PROTS_CONSUMPTION = 105F
    private const val ST_FATS_CONSUMPTION = 56F
    private const val ST_CARBS_CONSUMPTION = 170F
    private const val ST_KCALS_CONSUMPTION = 2400F

    const val PREF_PRIM_COLOR = "SHARED_PREFERENCE_PRIMARY_COLOR"
    const val PREF_SEC_COLOR = "SHARED_PREFERENCE_SECONDARY_COLOR"
    const val PREF_FONT = "SHARED_PREFERENCE_FONT"
    const val PREF_KEY: String = "THEME_SHARED_PREFERENCE"

    val DEFAULT_GOAL_CONSUMPTION = NutritionValueModel(
        prots = ST_PROTS_CONSUMPTION,
        fats = ST_FATS_CONSUMPTION,
        carbs = ST_CARBS_CONSUMPTION,
        kcals = ST_KCALS_CONSUMPTION,
    )

    const val COMPONENT_VAL_MIN = 0
    const val COMPONENT_VAL_MAX = 10000
}