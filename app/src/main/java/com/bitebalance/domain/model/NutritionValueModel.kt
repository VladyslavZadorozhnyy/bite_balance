package com.bitebalance.domain.model

import com.database.entities.NutritionValueEntity
import com.ui.model.NutritionValueModel

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

fun NutritionValueModel.toEntity(id: Long): NutritionValueEntity {
    return NutritionValueEntity(
        this.prots,
        this.fats,
        this.carbs,
        this.kcals,
        id = id
    )
}