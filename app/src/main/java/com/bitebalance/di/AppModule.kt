package com.bitebalance.di

import com.bitebalance.presentation.viewmodels.NavigationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { NavigationViewModel() }
}