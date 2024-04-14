package com.bitebalance.di

import org.koin.dsl.module
import com.database.db.AppDaoDatabase
import com.database.db.AppDaoDatabaseImpl
import com.bitebalance.domain.repository.*
import com.bitebalance.data.db_repository.*
import com.bitebalance.domain.usecase.get.*
import com.bitebalance.presentation.viewmodels.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.android.ext.koin.androidApplication
import com.bitebalance.domain.usecase.add.AddNewDishUseCase
import com.bitebalance.domain.usecase.add.AddNewMealUseCase
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.bitebalance.domain.usecase.remove.RemoveDishUseCase
import com.bitebalance.domain.usecase.remove.RemoveMealUseCase
import com.bitebalance.domain.usecase.add.AddNewDishAndMealUseCase
import com.bitebalance.domain.usecase.remove.RemoveAllMealsUseCase
import com.bitebalance.domain.usecase.update.UpdateNutritionValueUseCase
import com.bitebalance.domain.usecase.update.UpdateConsumptionGoalUseCase

val appModule = module {
//  ViewModels
    viewModel { ThemeViewModel(get()) }
    viewModel { NavigationViewModel() }
    viewModel { StatsViewModel(get()) }
    viewModel { DateViewModel(get(), get()) }
    viewModel { DishViewModel(get(), get(), get(), get()) }
    viewModel { NutritionViewModel(get(), get(), get(), get()) }
    viewModel { MealViewModel(get(), get(), get(), get(), get()) }

//  UseCases
    single { GetMonthUseCase(get()) }
    single { GetAllDishesUseCase(get()) }
    single { GetGreetingsUseCase(get(), get()) }
    single { GetDishByNameUseCase(get(), get()) }
    single { RemoveAllMealsUseCase(get(), get()) }
    single { GetNutritionValueUseCase(get(), get()) }
    single { RemoveDishUseCase(get(), get(), get()) }
    single { RemoveMealUseCase(get(), get(), get()) }
    single { UpdateConsumptionGoalUseCase(get(), get()) }
    single { AddNewDishUseCase(get(), get(), get(), get()) }
    single { GetAllMealsUseCase(get(), get(), get(), get()) }
    single { UpdateNutritionValueUseCase(get(), get(), get()) }
    single { AddNewMealUseCase(get(), get(), get(), get(), get()) }
    single<AppDaoDatabase> { AppDaoDatabaseImpl(androidApplication()) }
    single { GetConsumedGoalUseCase(get(), get(), get(), get(), get()) }
    single { AddNewDishAndMealUseCase(get(), get(), get(), get(), get()) }
    single { GetNutritionValuesByDateUseCase(get(), get(), get(), get(), get()) }

//  Repositories
    single<DishRepository> { DishRepositoryImpl(get()) }
    single<DateRepository> { DateRepositoryImpl(get()) }
    single<MealRepository> { MealRepositoryImpl(get()) }
    single<GoalRepository> { GoalRepositoryImpl(get()) }
    single<StringRepository> { StringRepositoryImpl(get()) }
    single<NutritionValueRepository> { NutritionValueRepositoryImpl(get()) }
}