package com.bitebalance.domain.usecase.remove

import com.ui.components.R
import com.ui.model.GoalModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.GoalRepository
import com.bitebalance.domain.repository.StringRepository

class RemoveGoalUseCase(
    private val goalRepository: GoalRepository,
    private val stringRepository: StringRepository,
) {
    operator fun invoke(goalToRemove: GoalModel): Flow<Resource<String>> = flow {
        emit(Resource.Loading())

        var resultMessage = ""
        var errorMessage = ""
        withContext(Dispatchers.IO) {
            try {
                goalRepository.removeGoal(goalToRemove)
                resultMessage = stringRepository.getStr(R.string.remove_success)
            } catch (exception: Exception) {
                errorMessage = exception.message ?: stringRepository.getStr(R.string.unknown_error)
            }
        }

        if (errorMessage.isNotEmpty()) emit(Resource.Error(errorMessage))
        else emit(Resource.Success(message = resultMessage))
    }
}