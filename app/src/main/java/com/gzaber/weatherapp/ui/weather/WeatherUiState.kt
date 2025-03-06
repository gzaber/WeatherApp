package com.gzaber.weatherapp.ui.weather

import com.gzaber.weatherapp.data.repository.locations.model.Location
import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeather
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeather
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeather
import com.gzaber.weatherapp.ui.util.emptyCurrentWeather
import com.gzaber.weatherapp.ui.util.emptyDailyWeather
import com.gzaber.weatherapp.ui.util.emptyHourlyWeather
import com.gzaber.weatherapp.ui.util.emptyLocation

data class WeatherUiState(
    val location: Location = emptyLocation(),
    val currentWeather: CurrentWeather = emptyCurrentWeather(),
    val hourlyWeather: HourlyWeather = emptyHourlyWeather(),
    val dailyWeather: DailyWeather = emptyDailyWeather(),
    val weatherForecastType: WeatherForecastType = WeatherForecastType.HOURLY,
    val isLoadingCurrentWeather: Boolean = false,
    val isLoadingHourlyWeather: Boolean = false,
    val isLoadingDailyWeather: Boolean = false,
    val isError: Boolean = false
)

enum class WeatherForecastType {
    DAILY, HOURLY
}
