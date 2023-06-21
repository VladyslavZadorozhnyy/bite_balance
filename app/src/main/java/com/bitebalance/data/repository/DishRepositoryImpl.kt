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

    override fun removeDish(dishModel: DishModel) {
        return appDaoDatabase.getDishDao().deleteByName(dishModel.name)
    }

    override fun getAllDishes(): List<DishModel> {
        return appDaoDatabase.getDishDao().getAll().map { DishModel.fromEntity(it) }
    }

    override fun getDishByName(name: String): DishModel? {
        return appDaoDatabase.getDishDao().getByName(name)?.let { DishModel.Companion.fromEntity(it) }
    }

    override fun getDishById(id: Long): DishModel? {
        return appDaoDatabase.getDishDao().getById(id)?.let { DishModel.Companion.fromEntity(it) }
    }
}