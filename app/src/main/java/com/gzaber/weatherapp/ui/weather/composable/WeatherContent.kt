package com.gzaber.weatherapp.ui.weather.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeather
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeather
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeather
import com.gzaber.weatherapp.ui.weather.WeatherForecastType
import com.gzaber.weatherapp.ui.weather.util.toDescription
import com.gzaber.weatherapp.ui.weather.util.toSymbol
import java.time.format.DateTimeFormatter

@Composable
fun WeatherContent(
    contentPadding: PaddingValues,
    currentWeather: CurrentWeather,
    hourlyWeather: HourlyWeather,
    dailyWeather: DailyWeather,
    weatherForecastType: WeatherForecastType,
    onWeatherForecastTypeChanged: (WeatherForecastType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Home,
            contentDescription = "Clear search text",
            modifier = Modifier
                .size(96.dp)
                .weight(1f)
        )
        Text(
            text = "${currentWeather.temperature.value}${currentWeather.temperature.unit.toSymbol()}",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = currentWeather.condition.toDescription(),
            style = MaterialTheme.typography.displayMedium
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            WeatherParameter(
                icon = Icons.Default.LocationOn,
                iconContentDescription = "Wind speed",
                value = "${currentWeather.windSpeed.value}",
                unit = currentWeather.windSpeed.unit.toSymbol(),
                description = "Wind"
            )
            WeatherParameter(
                icon = Icons.Default.AccountBox,
                iconContentDescription = "Humidity",
                value = "${currentWeather.humidity.value}",
                unit = currentWeather.humidity.unit.toSymbol(),
                description = "Humidity"
            )
            WeatherParameter(
                icon = Icons.Default.Build,
                iconContentDescription = "Rain",
                value = "${currentWeather.precipitation.value}",
                unit = currentWeather.precipitation.unit.toSymbol(),
                description = "Rain"
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                onClick = { onWeatherForecastTypeChanged(WeatherForecastType.HOURLY) }
            ) {
                Text(
                    text = "Today",
                    fontWeight = if (weatherForecastType == WeatherForecastType.HOURLY) FontWeight.Bold else FontWeight.Normal
                )
            }
            TextButton(
                onClick = { onWeatherForecastTypeChanged(WeatherForecastType.DAILY) }
            ) {
                Text(
                    text = "7 days",
                    fontWeight = if (weatherForecastType == WeatherForecastType.DAILY) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
        LazyRow {
            if (weatherForecastType == WeatherForecastType.HOURLY) {
                items(hourlyWeather.hourly) { weather ->
                    WeatherForecastCard(
                        time = weather.time.format(DateTimeFormatter.ofPattern("HH:mm")),
                        icon = Icons.Default.LocationOn,
                        value = "${weather.temperature}",
                        unit = hourlyWeather.temperatureUnit.toSymbol()
                    )
                }
            } else {
                items(dailyWeather.daily) { weather ->
                    WeatherForecastCard(
                        time = weather.date.format(DateTimeFormatter.ofPattern("EE")),
                        icon = Icons.Default.LocationOn,
                        value = "${weather.maxTemperature}",
                        unit = dailyWeather.temperatureUnit.toSymbol()
                    )
                }
            }
        }
    }
}