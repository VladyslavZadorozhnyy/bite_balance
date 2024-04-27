package com.bitebalance.domain.usecase.get

import com.ui.components.R
import java.text.SimpleDateFormat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.bitebalance.common.Resource
import com.ui.model.NutritionValueModel
import com.bitebalance.domain.repository.*

class GetNutritionValuesByDateUseCase(
    private val dateRepository: DateRepository,
    private val mealRepository: MealRepository,
    private val dishRepository: DishRepository,
    private val stringRepository: StringRepository,
    private val nutrRepository: NutritionValueRepository,
) {
    private val emptyNutritionValue = NutritionValueModel(0F, 0F, 0F, 0F)
    operator fun invoke(
        dateFormat: SimpleDateFormat,
        dateValue: String,
    ): Flow<Resource<Pair<List<NutritionValueModel>, NutritionValueModel>>> = flow {
        emit(Resource.Loading())

        val dayConsumptionMap: MutableMap<Int, NutritionValueModel> = mutableMapOf()
        var goalConsumption: NutritionValueModel? = null
        var errorMessage = ""

        withContext(Dispatchers.IO) {
            try {
                var summedNutr = emptyNutritionValue
                val dateModel = dateRepository.getDateFromString(dateFormat, dateValue)
                val daysCount = dateRepository.getDaysCountInMonth(dateModel.month, dateModel.year)

                for (i in 0 until daysCount)
                    dayConsumptionMap[i] = emptyNutritionValue

                mealRepository.getAllMeals()
                    .filter { dateRepository.getDateById(it.mealTimeId)?.month == dateModel.month &&
                            dateRepository.getDateById(it.mealTimeId)?.year == dateModel.year }
                    .groupBy {
                        dateRepository.getDateById(it.mealTimeId)?.day
                    }.asIterable().forEach { mapEntry ->
                        mapEntry.value.map { mealModel ->
                            val dishModel = dishRepository.getDishById(mealModel.dishId)
                            val nutModel = nutrRepository.getNutritionValueById(dishModel?.nutritionValId ?: -1)

                            nutModel?.let { summedNutr = summedNutr.plus(nutModel, mealModel.amount) }
                        }
                        mapEntry.key?.let {
                            dayConsumptionMap[it] = summedNutr
                            summedNutr = emptyNutritionValue
                        }
                    }
                nutrRepository.getGoalConsumption()?.let { goalConsumption = it }
            } catch (exception: Throwable) {
                errorMessage = exception.message ?: stringRepository.getStr(R.string.unknown_error)
            }
        }

        if (errorMessage.isNotEmpty()) {
            emit(Resource.Error(message = errorMessage))
        } else if (goalConsumption != null) {
            emit(Resource.Success(
                data = Pair(dayConsumptionMap.values.toList(), goalConsumption!!)
            ))
        } else {
            emit(Resource.Error(message = stringRepository.getStr(R.string.consumption_error)))
        }
    }
}