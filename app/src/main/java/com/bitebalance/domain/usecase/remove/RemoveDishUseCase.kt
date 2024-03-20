package com.bitebalance.domain.usecase.remove

import com.ui.components.R
import com.ui.model.DishModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.DishRepository
import com.bitebalance.domain.repository.StringRepository
import com.bitebalance.domain.repository.NutritionValueRepository

class RemoveDishUseCase(
    private val dishRepository: DishRepository,
    private val stringRepository: StringRepository,
    private val nutritionValueRepository: NutritionValueRepository,
) {
    operator fun invoke(dishName: String): Flow<Resource<List<DishModel>>> = flow {
        var resultMessage = ""
        emit(Resource.Loading())

        withContext(Dispatchers.IO) {
            try {
                dishRepository.getDishByName(dishName)?.let {
                    nutritionValueRepository.removeNutritionValueById(it.nutritionValId)
                    dishRepository.removeDish(it)
                }
            } catch (exception: Exception) {
                resultMessage = exception.message ?: stringRepository.getStr(R.string.unknown_error)
            }
        }

        if (resultMessage.isNotEmpty()) emit(Resource.Error(message = resultMessage))
        else emit(Resource.Success(message = stringRepository.getStr(R.string.remove_success)))
    }
}