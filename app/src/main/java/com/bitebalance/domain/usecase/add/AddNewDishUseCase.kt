package com.bitebalance.domain.usecase.add

import com.ui.components.R
import com.ui.model.DishModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.bitebalance.common.Resource
import com.bitebalance.common.Constants
import com.ui.model.NutritionValueModel
import com.bitebalance.domain.repository.DateRepository
import com.bitebalance.domain.repository.DishRepository
import com.bitebalance.domain.repository.StringRepository
import com.bitebalance.domain.repository.NutritionValueRepository


class AddNewDishUseCase(
    private val dishRepository: DishRepository,
    private val dateRepository: DateRepository,
    private val stringRepository: StringRepository,
    private val nutritionValueRepository: NutritionValueRepository,
) {
    operator fun invoke(
        name: String,
        prots: Float,
        fats: Float,
        carbs: Float,
        kcals: Float,
    ): Flow<Resource<List<DishModel>>> = flow {
        var resultMessage = ""
        emit(Resource.Loading())

        withContext(Dispatchers.IO) {
            try {
                dishRepository.getDishByName(name)?.let {
                    throw Exception(stringRepository.getStr(R.string.dish_exists_error))
                }

                if (prots < 0.0 || fats < 0.0 || carbs < 0.0 || kcals < 0.0)
                    throw Exception(stringRepository.getStr(R.string.negative_val_error))
                else if (name.isEmpty())
                    throw Exception(stringRepository.getStr(R.string.name_empty_error))

                val newNutritionValueId = nutritionValueRepository.addNutritionValue(
                    nutritionValueModel = NutritionValueModel(prots, fats, carbs, kcals)
                )

                dishRepository.addDish(
                    dishModel = DishModel(name, getDishIconRes(), newNutritionValueId)
                )
            } catch (exception: Exception) {
                resultMessage = exception.message ?: stringRepository.getStr(R.string.unknown_error)
            }
        }

        if (resultMessage.isNotEmpty()) emit(Resource.Error(message = resultMessage))
        else emit(Resource.Success(message = stringRepository.getStr(R.string.added)))
    }

    private fun getDishIconRes(): Int {
        val currentHour = dateRepository.getCurrentDate().hour

        return if (currentHour < Constants.BREAKFAST_HOUR) R.drawable.breakfast_icon
        else if (currentHour < Constants.LUNCH_HOUR) R.drawable.lunch_icon
        else R.drawable.dinner_icon
    }
}