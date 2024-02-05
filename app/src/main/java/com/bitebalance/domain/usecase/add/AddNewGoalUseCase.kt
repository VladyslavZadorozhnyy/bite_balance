package com.bitebalance.domain.usecase.add

import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.*
import com.ui.model.GoalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class AddNewGoalUseCase(
    private val goalRepository: GoalRepository,
    private val dateRepository: DateRepository,
) {

    operator fun invoke(
        textValue: String,
        active: Boolean = true,
        achieved: Boolean = false,
    ): Flow<Resource<String>> = flow {
        emit(Resource.Loading())

        var resultMessage = ""
        var errorMessage = ""
        withContext(Dispatchers.IO) {
            try {
                goalRepository.addGoal(
                    GoalModel(
                        textValue = textValue,
                        active = active,
                        achieved = achieved,
                        dateCreatedId = dateRepository.getCurrentDateId(),
                    )
                )

                resultMessage = "Goal created successfully"
            } catch (exception: Exception) {
                errorMessage = exception.message ?: "Unknown error"
            }
        }

        if (errorMessage.isNotEmpty()) {
            emit(Resource.Error(message = errorMessage))
        } else {
            emit(Resource.Success(message = resultMessage))
        }
    }
}