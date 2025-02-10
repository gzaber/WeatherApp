package com.gzaber.weatherapp.data.repository.weather

import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeather
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeather
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeather
import com.gzaber.weatherapp.data.repository.weather.model.toExternal
import com.gzaber.weatherapp.data.source.network.weather.WeatherApi

class DefaultWeatherRepository(
    private val networkDataSource: WeatherApi
) : WeatherRepository {
    override suspend fun getCurrentWeather(latitude: Double, longitude: Double): CurrentWeather =
        networkDataSource.getCurrentWeather(latitude, longitude).toExternal()

    override suspend fun getHourlyWeather(latitude: Double, longitude: Double): HourlyWeather =
        networkDataSource.getHourlyWeather(latitude, longitude).toExternal()

    override suspend fun getDailyWeather(latitude: Double, longitude: Double): DailyWeather =
        networkDataSource.getDailyWeather(latitude, longitude).toExternal()
}