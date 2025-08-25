package com.gzaber.weatherapp.data.repository.weather.model

data class CurrentWeatherParameter<U, V>(
    val unit: U,
    val value: V
)
