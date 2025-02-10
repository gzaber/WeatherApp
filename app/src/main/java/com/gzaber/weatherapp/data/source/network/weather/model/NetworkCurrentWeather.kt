package com.gzaber.weatherapp.data.source.network.weather.model

import com.google.gson.annotations.SerializedName

data class NetworkCurrentWeather(
    @SerializedName("current")
    val values: NetworkCurrentWeatherValues,
    @SerializedName("current_units")
    val units: NetworkCurrentWeatherUnits
)
