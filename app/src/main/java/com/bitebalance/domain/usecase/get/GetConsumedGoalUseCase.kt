package com.bitebalance.domain.usecase.get

import com.bitebalance.common.Resource
import com.bitebalance.domain.model.NutritionValueModel
import com.bitebalance.domain.repository.DateRepository
import com.bitebalance.domain.repository.MealRepository
import com.bitebalance.domain.repository.NutritionValueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class GetConsumedGoalUseCase(
    private val dateRepository: DateRepository,
    private val mealRepository: MealRepository,
    private val nutritionValueRepository: NutritionValueRepository,
) {
    operator fun invoke(): Flow<Resource<NutritionValueModel>> = flow {
        emit(Resource.Loading())

        withContext(Dispatchers.IO) {}
    }
}