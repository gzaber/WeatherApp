package com.gzaber.weatherapp.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class LocationEntity(
    @PrimaryKey val id: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val country: String
)
