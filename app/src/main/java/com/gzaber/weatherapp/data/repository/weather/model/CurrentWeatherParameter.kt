package com.gzaber.weatherapp.data.repository.weather.model

data class CurrentWeatherParameter<T>(
    val value: T,
    val unit: String
)
