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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gzaber.weatherapp.R
import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeather
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeather
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeather
import com.gzaber.weatherapp.ui.weather.util.toDescription
import com.gzaber.weatherapp.ui.weather.util.toDrawable
import com.gzaber.weatherapp.ui.weather.util.toSymbol
import java.time.format.DateTimeFormatter

@Composable
fun WeatherContent(
    contentPadding: PaddingValues,
    currentWeather: CurrentWeather,
    hourlyWeather: HourlyWeather,
    dailyWeather: DailyWeather,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = currentWeather.date.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")),
            style = MaterialTheme.typography.titleLarge
        )
        Icon(
            painter = painterResource(currentWeather.condition.toDrawable()),
            contentDescription = "Clear search text",
            modifier = Modifier
                .size(120.dp)
                .weight(1f)
        )
        Text(
            text = "${currentWeather.temperature.value.toInt()}${currentWeather.temperature.unit.toSymbol()}",
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
                icon = R.drawable.ic_wind,
                iconContentDescription = "Wind speed",
                value = "${currentWeather.windSpeed.value}",
                unit = currentWeather.windSpeed.unit.toSymbol(),
                description = "Wind"
            )
            WeatherParameter(
                icon = R.drawable.ic_humidity,
                iconContentDescription = "Humidity",
                value = "${currentWeather.humidity.value}",
                unit = currentWeather.humidity.unit.toSymbol(),
                description = "Humidity"
            )
            WeatherParameter(
                icon = R.drawable.ic_precipitation,
                iconContentDescription = "Precipitation",
                value = "${currentWeather.precipitation.value}",
                unit = currentWeather.precipitation.unit.toSymbol(),
                description = "Precipitation"
            )
        }
        LazyRow(modifier = Modifier.padding(bottom = 16.dp)) {
            items(hourlyWeather.hourly) { weather ->
                WeatherForecastCard(
                    topText = weather.time.format(DateTimeFormatter.ofPattern("HH:mm")),
                    icon = weather.condition.toDrawable(),
                    bottomText = "${weather.temperature.toInt()}${hourlyWeather.temperatureUnit.toSymbol()}"
                )
            }
        }
        LazyRow {
            items(dailyWeather.daily) { weather ->
                WeatherForecastCard(
                    topText = weather.date.format(DateTimeFormatter.ofPattern("EE")),
                    icon = weather.condition.toDrawable(),
                    bottomText = "${weather.maxTemperature.toInt()}${dailyWeather.temperatureUnit.toSymbol()}\n" +
                            "${weather.minTemperature.toInt()}${dailyWeather.temperatureUnit.toSymbol()}"
                )
            }
        }
    }
}