package com.gzaber.weatherapp.data.repository.weather.model

data class CurrentWeather(
    val weatherCode: Int,
    val temperature: CurrentWeatherParameter<TemperatureUnit, Double>,
    val humidity: CurrentWeatherParameter<HumidityUnit, Int>,
    val rain: CurrentWeatherParameter<PrecipitationUnit, Double>,
    val windSpeed: CurrentWeatherParameter<WindSpeedUnit, Double>
)
