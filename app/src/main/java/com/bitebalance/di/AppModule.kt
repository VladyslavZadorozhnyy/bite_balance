package com.bitebalance.di

import com.bitebalance.data.repository.DateRepositoryImpl
import com.bitebalance.data.repository.DishRepositoryImpl
import com.bitebalance.data.repository.MealRepositoryImpl
import com.bitebalance.data.repository.NutritionValueRepositoryImpl
import com.bitebalance.domain.repository.DateRepository
import com.bitebalance.domain.repository.DishRepository
import com.bitebalance.domain.repository.MealRepository
import com.bitebalance.domain.repository.NutritionValueRepository
import com.bitebalance.domain.usecase.AddNewDishAndMealUseCase
import com.bitebalance.domain.usecase.GetAllDishesUseCase
import com.bitebalance.domain.usecase.GetConsumedGoalUseCase
import com.bitebalance.domain.usecase.GetNutritionValueUseCase
import com.bitebalance.presentation.viewmodels.*
import com.database.db.AppDaoDatabase
import com.database.db.AppDaoDatabaseImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
//  ViewModels
    viewModel { NavigationViewModel() }
    viewModel { DishViewModel(get(), get()) }
    viewModel { MenuViewModel(get()) }
    viewModel { NutritionViewModel(get()) }
    viewModel { ConsumedGoalViewModel(get()) }

//  UseCases
    single { GetConsumedGoalUseCase(get(), get(), get()) }
    single { GetAllDishesUseCase(get()) }
    single { GetNutritionValueUseCase(get()) }
    single { AddNewDishAndMealUseCase(get(), get(), get(), get()) }
    single<AppDaoDatabase> { AppDaoDatabaseImpl(androidApplication()) }

//  Repositories
    single<DishRepository> { DishRepositoryImpl(get()) }
    single<DateRepository> { DateRepositoryImpl(get()) }
    single<MealRepository> { MealRepositoryImpl(get()) }
    single<NutritionValueRepository> { NutritionValueRepositoryImpl(get()) }
}