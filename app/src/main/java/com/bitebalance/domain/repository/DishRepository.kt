package com.bitebalance.domain.repository

import com.bitebalance.domain.model.DishModel

interface DishRepository {
    fun addDish(dishModel: DishModel): Long

    fun getAllDishes(): List<DishModel>

    fun getDishByName(name: String): DishModel
}