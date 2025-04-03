package com.gzaber.weatherapp.data.repository.locations.model

import com.gzaber.weatherapp.data.source.local.LocationEntity
import com.gzaber.weatherapp.data.source.network.location.model.NetworkLocation

fun Location.toLocal() = LocationEntity(id, latitude, longitude, name, country)

fun LocationEntity.toExternal() = Location(id, latitude, longitude, name, country)

fun NetworkLocation.toExternal() =
    Location(
        id = "$latitude$longitude",
        latitude = latitude,
        longitude = longitude,
        name = name,
        country = country
    )