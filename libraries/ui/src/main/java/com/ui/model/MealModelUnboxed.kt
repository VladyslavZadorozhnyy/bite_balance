package com.ui.model

data class MealModelUnboxed(
    val iconRes: Int,
    val dishId: Long,
    val dishName: String,
    val amount: Float,
    val mealTimeId: Long,
    val mealTime: String,
    val id: Long = 0,
) { companion object }