package com.gzaber.weatherapp.data.source.network.weather.model

import com.google.gson.annotations.SerializedName

data class NetworkCurrentWeatherUnits(
    @SerializedName("temperature_2m")
    val temperature: String,
    @SerializedName("relative_humidity_2m")
    val humidity: String,
    val rain: String,
    @SerializedName("wind_speed_10m")
    val windSpeed: String
)