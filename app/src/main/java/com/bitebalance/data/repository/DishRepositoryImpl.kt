package com.bitebalance.data.repository

import com.bitebalance.domain.model.fromEntity
import com.bitebalance.domain.model.toEntity
import com.bitebalance.domain.repository.DishRepository
import com.database.db.AppDaoDatabase
import com.ui.model.DishModel

class DishRepositoryImpl(
    private val appDaoDatabase: AppDaoDatabase
) : DishRepository {
    override fun addDish(dishModel: DishModel): Long {
        return appDaoDatabase.getDishDao().insert(dishModel.toEntity())
    }

    override fun getAllDishes(): List<DishModel> {
        return appDaoDatabase.getDishDao().getAll().map { DishModel.fromEntity(it) }
    }

    override fun getDishByName(name: String): DishModel {
        return DishModel.Companion.fromEntity(
            appDaoDatabase.getDishDao().getByName(name)
        )
    }
}