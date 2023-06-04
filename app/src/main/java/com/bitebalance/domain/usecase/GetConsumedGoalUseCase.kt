package com.bitebalance.domain.usecase

import android.util.Log
import com.bitebalance.common.Resource
import com.bitebalance.domain.model.NutritionValueModel
import com.bitebalance.domain.repository.DateRepository
import com.bitebalance.domain.repository.NutritionValueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class GetEatenTodayUseCase(
    private val dateRepository: DateRepository,
    private val nutritionValueRepository: NutritionValueRepository,
) {
    operator fun invoke(): Flow<Resource<NutritionValueModel>> = flow {

        emit(Resource.Loading())
        delay(7000)

        withContext(Dispatchers.IO) {}

        emit(Resource.Success(
            data = NutritionValueModel(
                prots = 10f,
                fats = 11f,
                carbs = 12f,
                kcals = 13f,
            )
        ))

//        Log.d("AAADIP", "dateRepository.getCurrentDate(): ${dateRepository.getCurrentDate()}")
////        Log.d("AAADIP", "nutritionValueRepository.removeGoalConsumption(): ${nutritionValueRepository.removeGoalConsumption()}")
//        nutritionValueRepository.setGoalConsumption(NutritionValueModel(
//            prots = 20f,
//            fats = 101f,
//            carbs = 30f,
//            kcals = 2000f
//        ))
//        Log.d("AAADIP", "nutritionValueRepository.getGoalConsumption(): ${nutritionValueRepository.getGoalConsumption()}")
    }
}