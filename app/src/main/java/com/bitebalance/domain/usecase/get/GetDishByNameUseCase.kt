package com.bitebalance.domain.usecase.get

import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.DishRepository
import com.ui.model.DishModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class GetDishByNameUseCase(
    private val dishRepository: DishRepository
) {
    operator fun invoke(dishName: String): Flow<Resource<DishModel>> = flow {
        emit(Resource.Loading())

        var resultData: DishModel? = null
        var errorMessage = ""

        withContext(Dispatchers.IO) {
            try {
                resultData = dishRepository.getDishByName(dishName)
            } catch (exception: Exception) {
                errorMessage = exception.message ?: "Unexpected error"
            }
        }

        if (resultData != null) {
            emit(Resource.Success(data = resultData))
        } else {
            emit(Resource.Error(message = errorMessage))
        }
    }
}