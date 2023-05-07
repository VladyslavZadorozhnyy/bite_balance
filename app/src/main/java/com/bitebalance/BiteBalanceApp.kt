package com.bitebalance

import android.app.Application
import com.bitebalance.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BiteBalanceApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BiteBalanceApp)
            modules(appModule)
        }
    }
}