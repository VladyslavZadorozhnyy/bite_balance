package com.ui.model

data class NutritionValueModel(
    val prots: Float,
    val fats: Float,
    val carbs: Float,
    val kcals: Float,
) {
    fun plus(other: NutritionValueModel, eatenAmount: Float): NutritionValueModel {
        return NutritionValueModel(
            prots = this.prots + (other.prots * eatenAmount / 100f),
            fats = this.fats + (other.fats * eatenAmount / 100f),
            carbs = this.carbs + (other.carbs * eatenAmount / 100f),
            kcals = this.kcals + (other.kcals * eatenAmount / 100f),
        )
    }
    companion object
}