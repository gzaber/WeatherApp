package com.gzaber.weatherapp.data.source.network.weather

import com.gzaber.weatherapp.data.source.network.weather.model.NetworkCurrentWeather
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkDailyWeather
import com.gzaber.weatherapp.data.source.network.weather.model.NetworkHourlyWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast")
    suspend fun getCurrentWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("temperature_unit") temperatureUnit: String,
        @Query("wind_speed_unit") windSpeedUnit: String,
        @Query("precipitation_unit") precipitationUnit: String,
        @Query("timezone") timezone: String = "auto",
        @Query("current") current: String = "temperature_2m,relative_humidity_2m,rain,weather_code,wind_speed_10m",
        @Query("forecast_days") forecastDays: Int = 1
    ): NetworkCurrentWeather

    @GET("forecast")
    suspend fun getHourlyWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("temperature_unit") temperatureUnit: String,
        @Query("wind_speed_unit") windSpeedUnit: String,
        @Query("precipitation_unit") precipitationUnit: String,
        @Query("timezone") timezone: String = "auto",
        @Query("hourly") current: String = "temperature_2m,weather_code",
        @Query("forecast_days") forecastDays: Int = 1
    ): NetworkHourlyWeather

    @GET("forecast")
    suspend fun getDailyWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("temperature_unit") temperatureUnit: String,
        @Query("wind_speed_unit") windSpeedUnit: String,
        @Query("precipitation_unit") precipitationUnit: String,
        @Query("timezone") timezone: String = "auto",
        @Query("daily") current: String = "weather_code,temperature_2m_max,temperature_2m_min"
    ): NetworkDailyWeather

    companion object {
        const val BASE_URL: String = "https://api.open-meteo.com/v1/"
    }
}