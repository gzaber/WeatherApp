package com.gzaber.weatherapp.data.repository.weather

import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeather
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeather
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeather
import com.gzaber.weatherapp.data.repository.weather.model.WeatherUnits

interface WeatherRepository {
    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
        weatherUnits: WeatherUnits
    ): CurrentWeather

    suspend fun getHourlyWeather(
        latitude: Double,
        longitude: Double,
        weatherUnits: WeatherUnits
    ): HourlyWeather

    suspend fun getDailyWeather(
        latitude: Double,
        longitude: Double,
        weatherUnits: WeatherUnits
    ): DailyWeather
}