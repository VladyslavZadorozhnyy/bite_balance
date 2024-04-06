package com.bitebalance.domain.usecase.get

import com.ui.components.R
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import com.bitebalance.common.Resource
import com.ui.model.NutritionValueModel
import com.bitebalance.domain.repository.StringRepository
import com.bitebalance.domain.repository.NutritionValueRepository

class GetNutritionValueUseCase(
    private val stringRepository: StringRepository,
    private val nutrRepository: NutritionValueRepository,
) {
    operator fun invoke(id: Long): Flow<Resource<NutritionValueModel>> = flow {
        emit(Resource.Loading())

        val result = withContext(Dispatchers.IO) {
            return@withContext nutrRepository.getNutritionValueById(id)
        }

        if (result == null) emit(Resource.Error(stringRepository.getStr(R.string.nutrition_not_found)))
        else emit(Resource.Success(data = result))
    }
}