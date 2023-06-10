package com.bitebalance.domain.repository

import com.ui.model.DishModel


interface DishRepository {
    fun addDish(dishModel: DishModel): Long

    fun getAllDishes(): List<DishModel>

    fun getDishByName(name: String): DishModel
}