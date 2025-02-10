package com.gzaber.weatherapp.data.repository.locations

import com.gzaber.weatherapp.data.repository.locations.model.Location

interface LocationsRepository {
    suspend fun search(name: String): List<Location>
    suspend fun insert(location: Location)
    suspend fun delete(location: Location)
    suspend fun readAll(): List<Location>
}