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
        mealTimeId = entity.mealTimeId,
        dishId = entity.dishId,
        amount = entity.amount,
        id = entity.id,
    )
}

fun MealModel.toEntity(): MealEntity {
    return MealEntity(
        mealTimeId = this.mealTimeId,
        dishId = this.dishId,
        amount = this.amount,
    )
}