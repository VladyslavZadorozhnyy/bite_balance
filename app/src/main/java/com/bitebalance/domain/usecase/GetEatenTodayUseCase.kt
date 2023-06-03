package com.bitebalance.domain.usecase

import android.util.Log
import com.bitebalance.domain.model.NutritionValueModel
import com.bitebalance.domain.repository.DateRepository
import com.bitebalance.domain.repository.NutritionValueRepository

class GetEatenTodayUseCase(
    private val dateRepository: DateRepository,
    private val nutritionValueRepository: NutritionValueRepository,
) {
    operator fun invoke() {
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