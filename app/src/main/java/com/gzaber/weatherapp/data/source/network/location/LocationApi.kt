package com.gzaber.weatherapp.data.source.network.location

import com.gzaber.weatherapp.data.source.network.location.model.NetworkLocationResults
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {
    @GET
    suspend fun search(@Query("name") name: String): NetworkLocationResults

    companion object {
        const val BASE_URL: String = "https://geocoding-api.open-meteo.com/v1/search"
    }
}