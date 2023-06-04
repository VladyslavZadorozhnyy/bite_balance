package com.bitebalance.domain.model

import com.database.entities.DishEntity
import com.database.entities.MealEntity

data class DishModel(
    val name: String,
    val iconRes: Int,
    val nutritionValId: Long
) { companion object }

fun DishModel.Companion.fromEntity(entity: DishEntity): DishModel {
    return DishModel(
        entity.name,
        entity.iconRes,
        entity.nutritionModelId
    )
}

fun DishModel.toEntity(): DishEntity {
    return DishEntity(
        this.name,
        this.iconRes,
        this.nutritionValId
    )
}