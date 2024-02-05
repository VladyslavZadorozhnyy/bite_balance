package com.bitebalance.domain.usecase.remove

import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.GoalRepository
import com.bitebalance.domain.repository.MealRepository
import com.ui.model.GoalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RemoveGoalUseCase(
    private val goalRepository: GoalRepository,
) {
    operator fun invoke(goalToRemove: GoalModel): Flow<Resource<String>> = flow {
        emit(Resource.Loading())

        var resultMessage = ""
        var errorMessage = ""
        withContext(Dispatchers.IO) {
            try {
                goalRepository.removeGoal(goalToRemove)
                resultMessage = "Removal is successful"
            } catch (exception: Exception) {
                errorMessage = exception.message ?: "Unknown message"
            }
        }

        if (errorMessage.isNotEmpty()) {
            emit(Resource.Error(errorMessage))
        } else {
            emit(Resource.Success(message = resultMessage))
        }
    }
}