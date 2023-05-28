package com.bitebalance.di

import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.database.db.AppDaoDatabase
import com.database.db.AppDaoDatabaseImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { NavigationViewModel() }

    single<AppDaoDatabase> { AppDaoDatabaseImpl(androidApplication()) }
}