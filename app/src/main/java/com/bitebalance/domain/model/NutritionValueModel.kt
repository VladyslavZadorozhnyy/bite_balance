package com.bitebalance.domain.model

import com.database.entities.NutritionValueEntity

data class NutritionValueModel(
    val prots: Float,
    val fats: Float,
    val carbs: Float,
    val kcals: Float
) { companion object }

fun NutritionValueModel.Companion.fromEntity(entity: NutritionValueEntity): NutritionValueModel {
    return NutritionValueModel(
        entity.prots,
        entity.fats,
        entity.carbs,
        entity.kcals,
    )
}

fun NutritionValueModel.toEntity(): NutritionValueEntity {
    return NutritionValueEntity(
        this.prots,
        this.fats,
        this.carbs,
        this.kcals,
    )
}