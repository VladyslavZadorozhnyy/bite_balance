package com.bitebalance.domain.usecase.remove

import com.ui.components.R
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import com.ui.model.MealModelUnboxed
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.DateRepository
import com.bitebalance.domain.repository.MealRepository
import com.bitebalance.domain.repository.StringRepository

class RemoveMealUseCase(
    private val mealRepository: MealRepository,
    private val dateRepository: DateRepository,
    private val stringRepository: StringRepository,
) {
    operator fun invoke(mealModelUnboxed: MealModelUnboxed): Flow<Resource<String>> = flow {
        emit(Resource.Loading())

        var resultMessage = ""
        var errorMessage = ""
        withContext(Dispatchers.IO) {
            try {
                mealRepository.removeMealById(mealModelUnboxed.id)
                dateRepository.removeById(mealModelUnboxed.mealTimeId)
                resultMessage = stringRepository.getStr(R.string.remove_success)
            } catch (exception: Exception) {
                errorMessage = exception.message ?: stringRepository.getStr(R.string.unknown_error)
            }
        }

        if (errorMessage.isNotEmpty()) emit(Resource.Error(errorMessage))
        else emit(Resource.Success(data = resultMessage))
    }
}