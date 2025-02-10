package com.gzaber.weatherapp.data.repository.weather.model

data class CurrentWeather(
    val weatherCode: Int,
    val temperature: CurrentWeatherParameter<Double>,
    val humidity: CurrentWeatherParameter<Int>,
    val rain: CurrentWeatherParameter<Int>,
    val windSpeed: CurrentWeatherParameter<Double>
)
