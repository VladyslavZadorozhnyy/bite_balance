package com.bitebalance.domain.usecase.get

import com.bitebalance.common.Resource
import com.ui.model.NutritionValueModel
import com.bitebalance.domain.repository.NutritionValueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class GetNutritionValueUseCase(
    private val nutritionValueRepository: NutritionValueRepository
) {
    operator fun invoke(id: Long): Flow<Resource<NutritionValueModel>> = flow {
        emit(Resource.Loading())

        val result = withContext(Dispatchers.IO) {
            return@withContext nutritionValueRepository.getNutritionValueById(id)
        }

        if (result == null) {
            emit(Resource.Error("Nutrition not found"))
        } else {
            emit(Resource.Success(data = result))
        }
    }
}