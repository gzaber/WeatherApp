package com.gzaber.weatherapp.data.repository.weather

import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeather
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeather
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeather
import com.gzaber.weatherapp.data.repository.weather.model.TemperatureUnit
import com.gzaber.weatherapp.data.repository.weather.model.WeatherUnits
import com.gzaber.weatherapp.data.repository.weather.model.toExternal
import com.gzaber.weatherapp.data.repository.weather.model.toQueryValue
import com.gzaber.weatherapp.data.source.network.weather.WeatherApi

class DefaultWeatherRepository(
    private val networkDataSource: WeatherApi
) : WeatherRepository {
    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
        weatherUnits: WeatherUnits
    ): CurrentWeather =
        networkDataSource.getCurrentWeather(
            latitude,
            longitude,
            weatherUnits.temperatureUnit.toQueryValue(),
            weatherUnits.windSpeedUnit.toQueryValue(),
            weatherUnits.precipitationUnit.toQueryValue()
        ).toExternal()

    override suspend fun getHourlyWeather(
        latitude: Double,
        longitude: Double,
        temperatureUnit: TemperatureUnit
    ): HourlyWeather =
        networkDataSource.getHourlyWeather(
            latitude,
            longitude,
            temperatureUnit.toQueryValue()
        ).toExternal()

    override suspend fun getDailyWeather(
        latitude: Double,
        longitude: Double,
        temperatureUnit: TemperatureUnit
    ): DailyWeather =
        networkDataSource.getDailyWeather(
            latitude,
            longitude,
            temperatureUnit.toQueryValue()
        ).toExternal()
}