package com.bitebalance.presentation.states

import com.ui.model.DishModel

data class MenuState(
    val dishes: List<DishModel>? = null,
    val errorMessage: String = "",
)