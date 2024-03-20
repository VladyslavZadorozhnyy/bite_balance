package com.bitebalance.domain.usecase.update

import com.ui.components.R
import com.ui.model.GoalModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.GoalRepository
import com.bitebalance.domain.repository.StringRepository

class UpdateGoalUseCase(
    private val goalRepository: GoalRepository,
    private val stringRepository: StringRepository,
) {
    operator fun invoke(goalModel: GoalModel, goalCompleted: Boolean): Flow<Resource<String?>> = flow {
        var resultMessage = ""

        emit(Resource.Loading())
        withContext(Dispatchers.IO) {
            try {
                goalRepository.updateGoal(
                    goalModel = GoalModel(
                        textValue = goalModel.textValue,
                        active = goalModel.active,
                        achieved = goalCompleted,
                        dateCreatedId = goalModel.dateCreatedId,
                        id = goalModel.id,
                    ),
                )
            } catch (e: Exception) {
                resultMessage = e.message ?: stringRepository.getStr(R.string.unknown_error)
            }
        }

        if (resultMessage.isNotEmpty()) emit(Resource.Error(message = resultMessage))
        else emit(Resource.Success(message = stringRepository.getStr(R.string.dish_update_success)))
    }
}