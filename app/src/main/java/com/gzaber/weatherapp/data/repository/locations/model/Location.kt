package com.gzaber.weatherapp.data.repository.locations.model

data class Location(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val description: String
)
