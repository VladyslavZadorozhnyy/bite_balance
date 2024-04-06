package com.bitebalance.domain.usecase.get

import com.ui.components.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.ui.model.MealModelUnboxed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.bitebalance.common.Resource
import com.bitebalance.domain.model.same
import com.bitebalance.domain.repository.DateRepository
import com.bitebalance.domain.repository.DishRepository
import com.bitebalance.domain.repository.MealRepository
import com.bitebalance.domain.repository.StringRepository

class GetAllMealsUseCase(
    private val mealRepository: MealRepository,
    private val dateRepository: DateRepository,
    private val dishRepository: DishRepository,
    private val stringRepository: StringRepository,
) {
    operator fun invoke(): Flow<Resource<List<MealModelUnboxed>>> = flow {
        emit(Resource.Loading())

        val result: MutableList<MealModelUnboxed> = mutableListOf()
        var errorMessage = ""
        withContext(Dispatchers.IO) {
            try {
                val todayDate = dateRepository.getCurrentDate()
                val meals = mealRepository.getAllMeals()
                val dates = meals.map { dateRepository.getDateById(it.mealTimeId) }
                val dishes = meals.map { dishRepository.getDishById(it.dishId) }

                for (i in meals.indices) {
                    if (dates[i]?.same(todayDate) != true) { continue }
                    result.add(
                        element = MealModelUnboxed(
                            iconRes = dishes[i]?.iconRes ?: R.drawable.no_dish_icon,
                            dishId = dishes[i]?.id ?: -1,
                            dishName = dishes[i]?.name ?: stringRepository.getStr(R.string.dish_removed),
                            amount = meals[i].amount,
                            mealTimeId = dates[i]?.id ?: -1,
                            mealTime = "${dates[i]?.hour}:${dates[i]?.minute}",
                            id = meals[i].id,
                        ),
                    )
                }
            } catch (exception: Exception) {
                errorMessage = exception.message ?: stringRepository.getStr(R.string.unknown_error)
            }
        }

        if (errorMessage.isNotEmpty()) emit(Resource.Error(errorMessage))
        else emit(Resource.Success(data = result))
    }
}