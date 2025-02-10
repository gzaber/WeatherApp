package com.gzaber.weatherapp.data.repository.locations.model

import com.gzaber.weatherapp.data.source.local.LocationEntity
import com.gzaber.weatherapp.data.source.network.location.model.NetworkLocation

fun Location.toLocal() = LocationEntity(id, name, latitude, longitude, description)

fun LocationEntity.toExternal() = Location(id, name, latitude, longitude, description)

fun NetworkLocation.toExternal() =
    Location(
        id = "$latitude$longitude",
        name = name,
        latitude = latitude,
        longitude = longitude,
        description = "$admin1 $admin2 $admin3 $admin4"
    )