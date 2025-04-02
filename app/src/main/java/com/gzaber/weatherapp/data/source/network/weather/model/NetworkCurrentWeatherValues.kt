package com.gzaber.weatherapp.data.source.network.weather.model

import com.google.gson.annotations.SerializedName

data class NetworkCurrentWeatherValues(
    @SerializedName("weather_code")
    val weatherCode: Int,
    @SerializedName("temperature_2m")
    val temperature: Double,
    @SerializedName("relative_humidity_2m")
    val humidity: Int,
    @SerializedName("wind_speed_10m")
    val windSpeed: Double,
    val precipitation: Double
)
