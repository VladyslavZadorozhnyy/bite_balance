package com.bitebalance.domain.usecase.update

import com.bitebalance.common.Resource
import com.ui.model.NutritionValueModel
import com.bitebalance.domain.repository.DishRepository
import com.bitebalance.domain.repository.GoalRepository
import com.bitebalance.domain.repository.NutritionValueRepository
import com.ui.model.GoalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class UpdateGoalUseCase(
    private val goalRepository: GoalRepository,
) {
    operator fun invoke(goalModel: GoalModel, goalCompleted: Boolean): Flow<Resource<String?>> = flow {
        var resultMessage = ""

        emit(Resource.Loading())
        withContext(Dispatchers.IO) {
            try {
                goalRepository.updateGoal(
                    GoalModel(
                        textValue = goalModel.textValue,
                        active = goalModel.active,
                        achieved = goalCompleted,
                        dateCreatedId = goalModel.dateCreatedId,
                        id = goalModel.id
                    )
                )
            } catch (e: Exception) {
                resultMessage = "Error occurred: ${e.message}"
            }
        }

        if (resultMessage.isNotEmpty()) {
            emit(Resource.Error(message = resultMessage))
        } else {
            emit(Resource.Success(message = "Dish updated successfully"))
        }
    }
}