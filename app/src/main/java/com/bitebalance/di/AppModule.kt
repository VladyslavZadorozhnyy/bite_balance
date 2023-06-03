package com.bitebalance.di

import com.bitebalance.data.repository.DateRepositoryImpl
import com.bitebalance.data.repository.NutritionValueRepositoryImpl
import com.bitebalance.domain.repository.DateRepository
import com.bitebalance.domain.repository.NutritionValueRepository
import com.bitebalance.domain.usecase.GetEatenTodayUseCase
import com.bitebalance.presentation.viewmodels.ConsumptionViewModel
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.database.db.AppDaoDatabase
import com.database.db.AppDaoDatabaseImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { NavigationViewModel() }

    viewModel { ConsumptionViewModel(get()) }

    single { GetEatenTodayUseCase(get(), get()) }

    single<AppDaoDatabase> { AppDaoDatabaseImpl(androidApplication()) }

    single<DateRepository> { DateRepositoryImpl(get()) }

    single<NutritionValueRepository> { NutritionValueRepositoryImpl(get()) }
}