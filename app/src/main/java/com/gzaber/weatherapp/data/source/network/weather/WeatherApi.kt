package com.gzaber.weatherapp.data.source.network.weather

import com.gzaber.weatherapp.data.source.network.weather.model.NetworkCurrentWeather
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkDailyWeather
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkHourlyWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET
    suspend fun getCurrentWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String = "temperature_2m,relative_humidity_2m,rain,weather_code,wind_speed_10m",
        @Query("forecast_days") forecastDays: Int = 1
    ): NetworkCurrentWeather

    @GET
    suspend fun getHourlyWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") current: String = "temperature_2m,weather_code",
        @Query("forecast_days") forecastDays: Int = 1
    ): NetworkHourlyWeather

    @GET
    suspend fun getDailyWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("daily") current: String = "weather_code,temperature_2m_max,temperature_2m_min"
    ): NetworkDailyWeather

    companion object {
        const val BASE_URL: String = "https://api.open-meteo.com/v1/forecast"
    }
}