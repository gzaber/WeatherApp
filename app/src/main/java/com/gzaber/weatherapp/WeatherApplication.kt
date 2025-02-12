package com.gzaber.weatherapp

import android.app.Application
import com.gzaber.weatherapp.di.dataModule
import com.gzaber.weatherapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApplication)
            modules(dataModule, viewModelModule)
        }
    }
}