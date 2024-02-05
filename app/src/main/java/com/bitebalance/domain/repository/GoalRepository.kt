package com.bitebalance.domain.repository

import com.ui.model.GoalModel

interface GoalRepository {
    fun addGoal(goalModel: GoalModel): Long
    fun removeGoal(goalModel: GoalModel)
    fun getAllGoals(): List<GoalModel>
    fun getGoalById(id: Long): GoalModel?
    fun updateGoal(goalModel: GoalModel)
}