package com.gzaber.weatherapp.data.source.network.weather.model

import com.google.gson.annotations.SerializedName

data class NetworkHourlyWeatherUnits(
    @SerializedName("temperature_2m")
    val temperature: String
)
