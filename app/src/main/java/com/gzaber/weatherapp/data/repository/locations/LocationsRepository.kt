package com.gzaber.weatherapp.data.repository.locations

import com.gzaber.weatherapp.data.repository.locations.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {

    suspend fun search(name: String): List<Location>

    suspend fun insert(location: Location)

    suspend fun delete(location: Location)

    fun observeAll(): Flow<List<Location>>
}