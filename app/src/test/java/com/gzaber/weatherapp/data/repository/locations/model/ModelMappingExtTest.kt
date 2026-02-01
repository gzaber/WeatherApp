package com.gzaber.weatherapp.data.repository.locations.model

import com.gzaber.weatherapp.data.source.local.LocationEntity
import com.gzaber.weatherapp.data.source.network.location.model.NetworkLocation
import org.junit.Assert.assertEquals
import org.junit.Test

class ModelMappingExtTest {

    @Test
    fun location_toLocal_returnsCorrectLocationEntity() {
        val location = Location(
            id = "52.2321.01",
            latitude = 52.23,
            longitude = 21.01,
            name = "Warsaw",
            country = "Poland"
        )
        val expectedEntity = LocationEntity(
            id = "52.2321.01",
            latitude = 52.23,
            longitude = 21.01,
            name = "Warsaw",
            country = "Poland"
        )

        val result = location.toLocal()

        assertEquals(expectedEntity, result)
    }

    @Test
    fun locationEntity_toExternal_returnsCorrectLocation() {
        val locationEntity = LocationEntity(
            id = "52.2321.01",
            latitude = 52.23,
            longitude = 21.01,
            name = "Warsaw",
            country = "Poland"
        )
        val expectedLocation = Location(
            id = "52.2321.01",
            latitude = 52.23,
            longitude = 21.01,
            name = "Warsaw",
            country = "Poland"
        )

        val result = locationEntity.toExternal()

        assertEquals(expectedLocation, result)
    }

    @Test
    fun networkLocation_toExternal_returnsCorrectLocation() {
        val networkLocation = NetworkLocation(
            name = "Warsaw",
            latitude = 52.23,
            longitude = 21.01,
            country = "Poland"
        )
        val expectedLocation = Location(
            id = "52.2321.01",
            latitude = 52.23,
            longitude = 21.01,
            name = "Warsaw",
            country = "Poland"
        )

        val result = networkLocation.toExternal()

        assertEquals(expectedLocation, result)
    }
}
