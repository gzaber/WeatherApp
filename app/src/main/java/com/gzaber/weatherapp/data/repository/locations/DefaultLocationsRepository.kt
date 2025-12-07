package com.gzaber.weatherapp.data.repository.locations

import com.gzaber.weatherapp.data.repository.locations.model.Location
import com.gzaber.weatherapp.data.repository.locations.model.toExternal
import com.gzaber.weatherapp.data.repository.locations.model.toLocal
import com.gzaber.weatherapp.data.source.local.LocationDao
import com.gzaber.weatherapp.data.source.network.location.LocationApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultLocationsRepository(
    private val localDataSource: LocationDao,
    private val networkDataSource: LocationApi
) : LocationsRepository {

    override suspend fun search(name: String): List<Location> =
        networkDataSource.search(name).results?.map { it.toExternal() } ?: emptyList()

    override suspend fun insert(location: Location) = localDataSource.insert(location.toLocal())

    override suspend fun delete(location: Location) = localDataSource.delete(location.toLocal())

    override fun observeAll(): Flow<List<Location>> =
        localDataSource.observeAll().map { locationEntities ->
            locationEntities.map { it.toExternal() }
        }
}