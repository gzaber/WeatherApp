package com.gzaber.weatherapp.data.source.network.weather.model

import com.google.gson.annotations.SerializedName

data class NetworkDailyWeather(
    @SerializedName("daily")
    val values: NetworkDailyWeatherValues,
    @SerializedName("daily_units")
    val units: NetworkDailyWeatherUnits
)
