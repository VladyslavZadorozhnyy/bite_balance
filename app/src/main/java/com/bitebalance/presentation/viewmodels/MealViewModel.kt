package com.bitebalance.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.bitebalance.domain.repository.MealRepository

class MealViewModel(
    private val mealRepository: MealRepository
): ViewModel() {
}