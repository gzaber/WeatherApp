package com.gzaber.weatherapp.data.repository.locations

import com.gzaber.weatherapp.data.repository.locations.model.Location
import com.gzaber.weatherapp.data.source.local.LocationDao
import com.gzaber.weatherapp.data.source.local.LocationEntity
import com.gzaber.weatherapp.data.source.network.location.LocationApi
import com.gzaber.weatherapp.data.source.network.location.model.NetworkLocation
import com.gzaber.weatherapp.data.source.network.location.model.NetworkLocationResults
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultLocationsRepositoryTest {

    private val localDataSource: LocationDao = mockk(relaxed = true)
    private val networkDataSource: LocationApi = mockk()
    private val repository = DefaultLocationsRepository(localDataSource, networkDataSource)

    @Test
    fun search_returnsConvertedLocations() = runTest {
        val networkLocations = listOf(
            NetworkLocation(
                name = "Warsaw",
                latitude = 52.23,
                longitude = 21.01,
                country = "Poland"
            )
        )

        coEvery { networkDataSource.search(any()) } returns NetworkLocationResults(networkLocations)

        val result = repository.search("Warsaw")

        assertEquals(1, result.size)
        assertEquals("Warsaw", result[0].name)
    }

    @Test
    fun insert_insertsLocationToLocalDataSource() = runTest {
        val location = Location(
            id = "1",
            name = "Warsaw",
            latitude = 52.23,
            longitude = 21.01,
            country = "Poland"
        )

        repository.insert(location)

        coVerify { localDataSource.insert(any()) }
    }

    @Test
    fun delete_deletesLocationFromLocalDataSource() = runTest {
        val location = Location(
            id = "1",
            name = "Warsaw",
            latitude = 52.23,
            longitude = 21.01,
            country = "Poland"
        )

        repository.delete(location)

        coVerify { localDataSource.delete(any()) }
    }

    @Test
    fun observeAll_returnsFlowOfLocations() = runTest {
        val locationEntities = listOf(
            LocationEntity(
                id = "1",
                name = "Warsaw",
                latitude = 52.23,
                longitude = 21.01,
                country = "Poland"
            )
        )
        every { localDataSource.observeAll() } returns flowOf(locationEntities)

        val result = repository.observeAll().first()

        assertEquals(1, result.size)
        assertEquals("Warsaw", result[0].name)
    }
}
