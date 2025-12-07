package com.gzaber.weatherapp.data.repository.weather.model

import java.time.LocalDateTime

data class CurrentWeather(
    val isDay: Boolean,
    val condition: WeatherCondition,
    val temperature: CurrentWeatherParameter<TemperatureUnit, Double>,
    val humidity: CurrentWeatherParameter<HumidityUnit, Int>,
    val windSpeed: CurrentWeatherParameter<WindSpeedUnit, Double>,
    val precipitation: CurrentWeatherParameter<PrecipitationUnit, Double>
)
