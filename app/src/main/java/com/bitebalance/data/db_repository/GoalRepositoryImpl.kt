package com.bitebalance.data.db_repository

import com.ui.model.GoalModel
import com.database.db.AppDaoDatabase
import com.bitebalance.domain.model.toEntity
import com.bitebalance.domain.model.fromEntity
import com.bitebalance.domain.repository.GoalRepository

class GoalRepositoryImpl(
    private val appDaoDatabase: AppDaoDatabase
) : GoalRepository {

    override fun addGoal(goalModel: GoalModel): Long {
        return appDaoDatabase.getGoalDao().insert(goalModel.toEntity())
    }

    override fun removeGoal(goalModel: GoalModel) {
        appDaoDatabase.getGoalDao().deleteById(goalModel.id)
    }

    override fun getAllGoals(): List<GoalModel> {
        return appDaoDatabase.getGoalDao().getAll().map { GoalModel.fromEntity(it) }
    }

    override fun getGoalById(id: Long): GoalModel? {
        return appDaoDatabase.getGoalDao().getById(id)?.let { GoalModel.fromEntity(it) }
    }

    override fun updateGoal(goalModel: GoalModel) {
        return appDaoDatabase.getGoalDao().updateItem(goalModel.toEntity())
    }
}