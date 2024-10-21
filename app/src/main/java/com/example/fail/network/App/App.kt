package com.example.fail.network.App

import android.app.Application
import com.example.fail.network.di.appModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class App:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            androidLogger()
            modules(appModule)
        }
    }
}