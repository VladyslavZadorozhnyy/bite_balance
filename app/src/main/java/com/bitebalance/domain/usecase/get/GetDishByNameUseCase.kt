package com.bitebalance.domain.usecase.get

import com.ui.components.R
import com.ui.model.DishModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.DishRepository
import com.bitebalance.domain.repository.StringRepository

class GetDishByNameUseCase(
    private val dishRepository: DishRepository,
    private val stringRepository: StringRepository,
) {
    operator fun invoke(dishName: String): Flow<Resource<DishModel>> = flow {
        emit(Resource.Loading())

        var resultData: DishModel? = null
        var errorMessage = ""

        withContext(Dispatchers.IO) {
            try {
                resultData = dishRepository.getDishByName(dishName)
            } catch (exception: Exception) {
                errorMessage = exception.message ?: stringRepository.getStr(R.string.unknown_error)
            }
        }

        if (resultData != null) emit(Resource.Success(data = resultData))
        else emit(Resource.Error(message = errorMessage))
    }
}