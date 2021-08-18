package com.utku.nasarover.app

import android.app.Application
import com.utku.nasarover.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NasaRoverApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NasaRoverApp)
            androidLogger(Level.ERROR)
            modules(allModules)
        }
    }
}