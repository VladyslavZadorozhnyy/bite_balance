package com.bitebalance.domain.model

import com.database.entities.DishEntity
import com.ui.model.DishModel

fun DishModel.Companion.fromEntity(entity: DishEntity): DishModel {
    return DishModel(
        entity.name,
        entity.iconRes,
        entity.nutritionModelId,
        entity.id
    )
}

fun DishModel.toEntity(): DishEntity {
    return DishEntity(
        this.name,
        this.iconRes,
        this.nutritionValId
    )
}