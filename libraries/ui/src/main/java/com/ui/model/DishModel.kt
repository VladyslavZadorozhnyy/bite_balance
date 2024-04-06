package com.ui.model

data class DishModel(
    val name: String,
    val iconRes: Int,
    val nutritionValId: Long,
    val id: Long = -1,
) { companion object }