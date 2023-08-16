package com.ui.model

data class NutritionValueModel(
    val prots: Float,
    val fats: Float,
    val carbs: Float,
    val kcals: Float
) {
    operator fun plus(other: NutritionValueModel): NutritionValueModel {
        return NutritionValueModel(
            prots = this.prots + other.prots,
            fats = this.fats + other.fats,
            carbs = this.carbs + other.carbs,
            kcals = this.kcals + other.kcals,
        )
    }
    companion object
}