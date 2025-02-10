package com.gzaber.weatherapp.data.source.network.weather.model

import com.google.gson.annotations.SerializedName

data class NetworkDailyWeatherUnits(
    @SerializedName("temperature_2m_min")
    val minTemperature: String,
    @SerializedName("temperature_2m_max")
    val maxTemperature: String
)
