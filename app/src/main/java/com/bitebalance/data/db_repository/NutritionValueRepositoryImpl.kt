package com.bitebalance.data.db_repository

import com.bitebalance.domain.model.fromEntity
import com.bitebalance.domain.model.toEntity
import com.bitebalance.domain.repository.NutritionValueRepository
import com.database.common.Constants
import com.database.db.AppDaoDatabase
import com.database.entities.GoalMetricsEntity
import com.ui.model.NutritionValueModel

class NutritionValueRepositoryImpl(
    private val appDaoDatabase: AppDaoDatabase
) : NutritionValueRepository {
    override fun getNutritionValueById(id: Long): NutritionValueModel? {
        return appDaoDatabase.getNutritionValueDao().getById(id)
            ?.let { NutritionValueModel.fromEntity(it) }
    }

    override fun updateNutritionValueById(id: Long, nutritionValueModel: NutritionValueModel) {
        return appDaoDatabase.getNutritionValueDao().updateItem(nutritionValueModel.toEntity(id))
    }

    override fun addNutritionValue(nutritionValueModel: NutritionValueModel): Long {
        return appDaoDatabase.getNutritionValueDao().insert(nutritionValueModel.toEntity())
    }

    override fun removeNutritionValue(nutritionValueModel: NutritionValueModel) {
        return appDaoDatabase.getNutritionValueDao().deleteItem(nutritionValueModel.toEntity())
    }

    override fun removeNutritionValueById(id: Long) {
        return appDaoDatabase.getNutritionValueDao().deleteById(id)
    }

    override fun getGoalConsumption(): NutritionValueModel? {
        val goalConsumptionEntity = appDaoDatabase
            .getGoalMetricsDao()
            .getByTableName(Constants.NUTRITION_VALUE_TABLE_NAME) ?: return null

        appDaoDatabase.getNutritionValueDao().getById(goalConsumptionEntity.entityId)
            ?.let { return NutritionValueModel.fromEntity(it) }

        return null
    }

    override fun setGoalConsumption(model: NutritionValueModel) {
        val updatedModelId = appDaoDatabase
            .getNutritionValueDao()
            .insert(model.toEntity())
        val previousGoalConsumption = appDaoDatabase
            .getGoalMetricsDao()
            .getByTableName(Constants.NUTRITION_VALUE_TABLE_NAME)

        if (previousGoalConsumption == null) {
            appDaoDatabase.getGoalMetricsDao().insert(GoalMetricsEntity(
                tableName = Constants.NUTRITION_VALUE_TABLE_NAME,
                entityId = updatedModelId
            ))
        } else {
            appDaoDatabase.getGoalMetricsDao().updateItem(
                newEntityId = updatedModelId,
                tableName = Constants.NUTRITION_VALUE_TABLE_NAME
            )
        }
    }

    override fun removeGoalConsumption() {
        appDaoDatabase.getGoalMetricsDao().deleteByTableName(
            tableName = Constants.NUTRITION_VALUE_TABLE_NAME
        )
    }
}