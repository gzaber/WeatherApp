package com.gzaber.weatherapp.data.source.network.weather.model

import com.google.gson.annotations.SerializedName

data class NetworkHourlyWeather(
    @SerializedName("hourly")
    val values: NetworkHourlyWeatherValues,
    @SerializedName("hourly_units")
    val units: NetworkHourlyWeatherUnits
)
