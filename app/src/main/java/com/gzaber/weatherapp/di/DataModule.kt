package com.gzaber.weatherapp.di

import androidx.room.Room
import com.gzaber.weatherapp.data.repository.locations.DefaultLocationsRepository
import com.gzaber.weatherapp.data.repository.locations.LocationsRepository
import com.gzaber.weatherapp.data.repository.weather.DefaultWeatherRepository
import com.gzaber.weatherapp.data.repository.weather.WeatherRepository
import com.gzaber.weatherapp.data.source.local.LocationDao
import com.gzaber.weatherapp.data.source.local.LocationsDatabase
import com.gzaber.weatherapp.data.source.network.location.LocationApi
import com.gzaber.weatherapp.data.source.network.weather.WeatherApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<LocationsDatabase> {
        Room.databaseBuilder(
            androidContext(),
            LocationsDatabase::class.java,
            "locations_db"
        ).build()
    }

    single<LocationDao> {
        get<LocationsDatabase>().locationDao()
    }

    single<LocationApi> {
        Retrofit.Builder()
            .baseUrl(LocationApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LocationApi::class.java)
    }

    single<WeatherApi> {
        Retrofit.Builder()
            .baseUrl(WeatherApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    single<LocationsRepository> {
        DefaultLocationsRepository(get(), get())
    }

    single<WeatherRepository> {
        DefaultWeatherRepository(get())
    }
}