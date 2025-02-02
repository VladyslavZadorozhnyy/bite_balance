package com.bitebalance

import android.app.Application
import com.bitebalance.di.appModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidLogger
import org.koin.android.ext.koin.androidContext

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