package com.bitebalance.domain.usecase.remove

import com.ui.components.R
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.MealRepository
import com.bitebalance.domain.repository.StringRepository

class RemoveAllMealsUseCase(
    private val mealRepository: MealRepository,
    private val stringRepository: StringRepository,
) {
    operator fun invoke(): Flow<Resource<String>> = flow {
        emit(Resource.Loading())

        var resultMessage = ""
        var errorMessage = ""
        withContext(Dispatchers.IO) {
            try {
                mealRepository.removeAllMeals()
                resultMessage = stringRepository.getStr(R.string.reset_success)
            } catch (exception: Exception) {
                errorMessage = exception.message ?: stringRepository.getStr(R.string.unknown_error)
            }
        }

        if (errorMessage.isNotEmpty()) emit(Resource.Error(errorMessage))
        else emit(Resource.Success(data = resultMessage))
    }
}