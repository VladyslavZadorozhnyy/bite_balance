package com.bitebalance.domain.usecase.add

import com.bitebalance.common.Resource
import com.bitebalance.domain.model.NutritionValueModel
import com.bitebalance.domain.repository.DateRepository
import com.bitebalance.domain.repository.DishRepository
import com.bitebalance.domain.repository.NutritionValueRepository
import com.ui.components.R
import com.ui.model.DishModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddNewDishUseCase(
    private val dishRepository: DishRepository,
    private val dateRepository: DateRepository,
    private val nutritionValueRepository: NutritionValueRepository,
) {
    operator fun invoke(name: String, prots: Float, fats: Float,
        carbs: Float, kcals: Float): Flow<Resource<List<DishModel>>> = flow {
        var resultMessage = ""
        emit(Resource.Loading())

        withContext(Dispatchers.IO) {
            try {
                dishRepository.getDishByName(name)?.let {
                    throw Exception("Dish with this name already exists")
                }

                if (prots < 0.0 || fats < 0.0 || carbs < 0.0 || kcals < 0.0) {
                    throw Exception("Negative values are not allowed")
                } else if (name.isEmpty()) {
                    throw Exception("'Name' field cannot be empty")
                }

                val newNutritionValueId = nutritionValueRepository.addNutritionValue(
                    nutritionValueModel = NutritionValueModel(prots, fats, carbs, kcals)
                )

                dishRepository.addDish(
                    dishModel = DishModel(name, getDishIconRes(), newNutritionValueId)
                )
            } catch (exception: Exception) {
                resultMessage = exception.message ?: "Unknown error"
            }
        }

        if (resultMessage.isNotEmpty()) {
            emit(Resource.Error(message = resultMessage))
        } else {
            emit(Resource.Success(message = "Added successfully"))
        }
    }

    private fun getDishIconRes(): Int {
        val currentHour = dateRepository.getCurrentDate().hour

        return if (currentHour < 12) {
            R.drawable.breakfast_icon
        } else if (currentHour < 19){
            R.drawable.lunch_icon
        } else {
            R.drawable.dinner_icon
        }
    }
}