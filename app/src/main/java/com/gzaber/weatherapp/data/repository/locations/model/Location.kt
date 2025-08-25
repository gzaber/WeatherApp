package com.gzaber.weatherapp.data.repository.locations.model

data class Location(
    val id: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val country: String
)
