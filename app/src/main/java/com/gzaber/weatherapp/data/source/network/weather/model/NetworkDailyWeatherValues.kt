package com.gzaber.weatherapp.data.source.network.weather.model

import com.google.gson.annotations.SerializedName

data class NetworkDailyWeatherValues(
    @SerializedName("time")
    val date: List<String>,
    @SerializedName("weather_code")
    val weatherCodes: List<Int>,
    @SerializedName("temperature_2m_min")
    val minTemperatures: List<Double>,
    @SerializedName("temperature_2m_max")
    val maxTemperature: List<Double>
)
