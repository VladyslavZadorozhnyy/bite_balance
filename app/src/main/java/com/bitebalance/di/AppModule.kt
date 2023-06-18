package com.bitebalance.di

import com.bitebalance.data.repository.DateRepositoryImpl
import com.bitebalance.data.repository.DishRepositoryImpl
import com.bitebalance.data.repository.MealRepositoryImpl
import com.bitebalance.data.repository.NutritionValueRepositoryImpl
import com.bitebalance.domain.repository.DateRepository
import com.bitebalance.domain.repository.DishRepository
import com.bitebalance.domain.repository.MealRepository
import com.bitebalance.domain.repository.NutritionValueRepository
import com.bitebalance.domain.usecase.add.AddNewDishAndMealUseCase
import com.bitebalance.domain.usecase.add.AddNewDishUseCase
import com.bitebalance.domain.usecase.get.GetAllDishesUseCase
import com.bitebalance.domain.usecase.get.GetConsumedGoalUseCase
import com.bitebalance.domain.usecase.get.GetDishByNameUseCase
import com.bitebalance.domain.usecase.get.GetNutritionValueUseCase
import com.bitebalance.domain.usecase.remove.RemoveDishUseCase
import com.bitebalance.domain.usecase.update.UpdateNutritionValueUseCase
import com.bitebalance.presentation.viewmodels.*
import com.database.db.AppDaoDatabase
import com.database.db.AppDaoDatabaseImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
//  ViewModels
    viewModel { NavigationViewModel() }
    viewModel { DishViewModel(get(), get(), get(), get(), get()) }
    viewModel { MenuViewModel(get()) }
    viewModel { NutritionViewModel(get(), get()) }
    viewModel { ConsumedGoalViewModel(get()) }

//  UseCases
    single { GetConsumedGoalUseCase(get(), get(), get()) }
    single { GetAllDishesUseCase(get()) }
    single { GetDishByNameUseCase(get()) }
    single { GetNutritionValueUseCase(get()) }
    single { RemoveDishUseCase(get(), get()) }
    single { UpdateNutritionValueUseCase(get(), get()) }
    single { AddNewDishAndMealUseCase(get(), get(), get(), get()) }
    single { AddNewDishUseCase(get(), get(), get()) }
    single<AppDaoDatabase> { AppDaoDatabaseImpl(androidApplication()) }

//  Repositories
    single<DishRepository> { DishRepositoryImpl(get()) }
    single<DateRepository> { DateRepositoryImpl(get()) }
    single<MealRepository> { MealRepositoryImpl(get()) }
    single<NutritionValueRepository> { NutritionValueRepositoryImpl(get()) }
}