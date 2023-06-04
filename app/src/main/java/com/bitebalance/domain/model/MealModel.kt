package com.bitebalance.domain.model

import com.database.entities.MealEntity

data class MealModel(
    val mealTimeId: Long,
    val dishId: Long,
    val amount: Float
) { companion object }

fun MealModel.Companion.fromEntity(entity: MealEntity): MealModel {
    return MealModel(
        entity.mealTimeId,
        entity.dishId,
        entity.amount
    )
}

fun MealModel.toEntity(): MealEntity {
    return MealEntity(
        this.mealTimeId,
        this.dishId,
        this.amount
    )
}