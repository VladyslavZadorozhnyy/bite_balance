package com.bitebalance.domain.model

import com.ui.model.NutritionValueModel
import com.database.entities.NutritionValueEntity

fun NutritionValueModel.Companion.fromEntity(entity: NutritionValueEntity): NutritionValueModel {
    return NutritionValueModel(
        prots = entity.prots,
        fats = entity.fats,
        carbs = entity.carbs,
        kcals = entity.kcals,
    )
}

fun NutritionValueModel.toEntity(): NutritionValueEntity {
    return NutritionValueEntity(
        prots = this.prots,
        fats = this.fats,
        carbs = this.carbs,
        kcals = this.kcals,
    )
}

fun NutritionValueModel.toEntity(id: Long): NutritionValueEntity {
    return NutritionValueEntity(
        prots = this.prots,
        fats = this.fats,
        carbs = this.carbs,
        kcals = this.kcals,
        id = id,
    )
}