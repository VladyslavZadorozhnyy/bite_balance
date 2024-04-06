package com.bitebalance.domain.usecase.add

import com.ui.components.R
import com.ui.model.GoalModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.*

class AddNewGoalUseCase(
    private val goalRepository: GoalRepository,
    private val dateRepository: DateRepository,
    private val stringRepository: StringRepository,
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
                    goalModel = GoalModel(
                        textValue = textValue,
                        active = active,
                        achieved = achieved,
                        dateCreatedId = dateRepository.getCurrentDateId(),
                    ),
                )
                resultMessage = stringRepository.getStr(R.string.goal_created)
            } catch (exception: Exception) {
                errorMessage = exception.message ?: stringRepository.getStr(R.string.unknown_error)
            }
        }

        if (errorMessage.isNotEmpty()) emit(Resource.Error(message = errorMessage))
        else emit(Resource.Success(message = resultMessage))
    }
}