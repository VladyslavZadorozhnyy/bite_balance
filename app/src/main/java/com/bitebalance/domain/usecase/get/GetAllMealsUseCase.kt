package com.bitebalance.domain.usecase.get

import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.DateRepository
import com.bitebalance.domain.repository.DishRepository
import com.bitebalance.domain.repository.MealRepository
import com.ui.model.MealModelUnboxed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class GetAllMealsUseCase(
    private val mealRepository: MealRepository,
    private val dateRepository: DateRepository,
    private val dishRepository: DishRepository,
) {
    operator fun invoke(): Flow<Resource<List<MealModelUnboxed>>> = flow {
        emit(Resource.Loading())

        var result: MutableList<MealModelUnboxed> = mutableListOf()
        var errorMessage = ""
        withContext(Dispatchers.IO) {
            try {
                val meals = mealRepository.getAllMeals()
                val dates = meals.map { dateRepository.getDateById(it.mealTimeId) }
                val dishes = meals.map { dishRepository.getDishById(it.dishId) }

                for (i in meals.indices) {
                    result.add(
                        MealModelUnboxed(
                            iconRes = dishes[i]?.iconRes ?: -1, // AAADIP need some error image
                            dishId = dishes[i]?.id ?: -1,
                            dishName = dishes[i]?.name ?: "Dish removed",
                            amount = meals[i].amount,
                            mealTimeId = dates[i]?.id ?: -1,
                            mealTime = "${dates[i]?.hour}:${dates[i]?.minute}",
                            id = meals[i].id,
                        )
                    )
                }

            } catch (exception: Exception) {
                errorMessage = exception.message ?: "Unknown error"
            }
        }

        if (errorMessage.isNotEmpty()) {
            emit(Resource.Error(errorMessage))
        } else {
            emit(Resource.Success(data = result))
        }
    }
}