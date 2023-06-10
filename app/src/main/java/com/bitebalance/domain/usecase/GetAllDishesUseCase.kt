package com.bitebalance.domain.usecase

import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.DishRepository
import com.ui.model.DishModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetAllDishesUseCase(
    private val dishRepository: DishRepository
) {
    operator fun invoke(): Flow<Resource<List<DishModel>>> = flow {
        emit(Resource.Loading())

        val result = withContext(Dispatchers.IO) {
            return@withContext dishRepository.getAllDishes()
        }
        emit(Resource.Success(result))
    }
}