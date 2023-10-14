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
import com.bitebalance.domain.usecase.add.AddNewMealUseCase
import com.bitebalance.domain.usecase.get.*
import com.bitebalance.domain.usecase.remove.RemoveAllMealsUseCase
import com.bitebalance.domain.usecase.remove.RemoveDishUseCase
import com.bitebalance.domain.usecase.remove.RemoveMealUseCase
import com.bitebalance.domain.usecase.update.UpdateNutritionValueUseCase
import com.bitebalance.presentation.viewmodels.*
import com.database.db.AppDaoDatabase
import com.database.db.AppDaoDatabaseImpl
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
//  ViewModels
    viewModel { ThemeViewModel(get()) }
    viewModel { NavigationViewModel() }
    viewModel { MealViewModel(get(), get(), get(), get(), get()) }
    viewModel { DateViewModel(get(), get()) }
    viewModel { StatsViewModel(get()) }
    viewModel { NutritionViewModel(get(), get(), get()) }
    viewModel { DishViewModel(get(), get(), get(), get(), get()) }

//  UseCases
    single { GetConsumedGoalUseCase(get(), get(), get(), get()) }
    single { GetMonthUseCase() }
    single { GetAllDishesUseCase(get()) }
    single { GetGreetingsUseCase(get()) }
    single { GetDishByNameUseCase(get()) }
    single { GetNutritionValueUseCase(get()) }
    single { RemoveDishUseCase(get(), get()) }
    single { RemoveMealUseCase(get(), get()) }
    single { RemoveAllMealsUseCase(get()) }
    single { GetNutritionValuesByDateUseCase(get(), get(), get(), get()) }
    single { GetAllMealsUseCase(get(), get(), get()) }
    single { UpdateNutritionValueUseCase(get(), get()) }
    single { AddNewMealUseCase(get(), get(), get(), get()) }
    single { AddNewDishAndMealUseCase(get(), get(), get(), get()) }
    single { AddNewDishUseCase(get(), get(), get()) }
    single<AppDaoDatabase> { AppDaoDatabaseImpl(androidApplication()) }

//  Repositories
    single<DishRepository> { DishRepositoryImpl(get()) }
    single<DateRepository> { DateRepositoryImpl(get()) }
    single<MealRepository> { MealRepositoryImpl(get()) }
    single<NutritionValueRepository> { NutritionValueRepositoryImpl(get()) }
}