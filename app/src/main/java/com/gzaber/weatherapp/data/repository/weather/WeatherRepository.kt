package com.gzaber.weatherapp.data.repository.weather

import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeather
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeather
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeather

interface WeatherRepository {
    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
    ): CurrentWeather

    suspend fun getHourlyWeather(
        latitude: Double,
        longitude: Double,
    ): HourlyWeather

    suspend fun getDailyWeather(
        latitude: Double,
        longitude: Double,
    ): DailyWeather
}