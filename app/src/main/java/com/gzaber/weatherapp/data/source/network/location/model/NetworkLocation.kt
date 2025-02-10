package com.gzaber.weatherapp.data.source.network.location.model

data class NetworkLocation(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val country: String,
    val admin1: String,
    val admin2: String,
    val admin3: String,
    val admin4: String
)
