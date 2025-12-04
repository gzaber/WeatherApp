package com.gzaber.weatherapp.data.source.network.weather.model

import com.google.gson.annotations.SerializedName

data class NetworkHourlyWeatherValues(
    @SerializedName("time")
    val time: List<String>,
    @SerializedName("is_day")
    val isDay: List<Int>,
    @SerializedName("weather_code")
    val weatherCodes: List<Int>,
    @SerializedName("temperature_2m")
    val temperatures: List<Double>
)
