package com.bitebalance.domain.model

import com.database.entities.MealEntity

data class MealModel(
    val mealTimeId: Long,
    val dishId: Long,
    val amount: Float,
    val id: Long = 0
) { companion object }

fun MealModel.Companion.fromEntity(entity: MealEntity): MealModel {
    return MealModel(
        entity.mealTimeId,
        entity.dishId,
        entity.amount,
        entity.id,
    )
}

fun MealModel.toEntity(): MealEntity {
    return MealEntity(
        this.mealTimeId,
        this.dishId,
        this.amount,
    )
}