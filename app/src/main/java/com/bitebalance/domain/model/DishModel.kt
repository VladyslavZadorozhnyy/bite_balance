package com.bitebalance.domain.model

import com.ui.model.DishModel
import com.database.entities.DishEntity

fun DishModel.Companion.fromEntity(entity: DishEntity): DishModel {
    return DishModel(
        name = entity.name,
        iconRes = entity.iconRes,
        nutritionValId = entity.nutritionModelId,
        id = entity.id,
    )
}

fun DishModel.toEntity(): DishEntity {
    return DishEntity(
        name = this.name,
        iconRes = this.iconRes,
        nutritionModelId = this.nutritionValId,
    )
}