package com.gzaber.weatherapp.ui.weather.composable

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gzaber.weatherapp.R
import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeather
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeather
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeather
import com.gzaber.weatherapp.ui.weather.util.toDescription
import com.gzaber.weatherapp.ui.weather.util.toIconRes
import com.gzaber.weatherapp.ui.weather.util.toSymbol
import kotlinx.coroutines.launch
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
            text = stringResource(R.string.weather_now_label),
            style = MaterialTheme.typography.headlineMedium
        )
        Image(
            painter = painterResource(currentWeather.condition.toIconRes(currentWeather.isDay)),
            contentDescription = stringResource(R.string.weather_condition_image_content_description),
            modifier = Modifier
                .size(256.dp)
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
                iconContentDescription = stringResource(R.string.wind_speed_icon_content_description),
                value = "${currentWeather.windSpeed.value}",
                unit = currentWeather.windSpeed.unit.toSymbol(),
                description = stringResource(R.string.wind_label)
            )
            WeatherParameter(
                icon = R.drawable.ic_humidity,
                iconContentDescription = stringResource(R.string.humidity_icon_content_description),
                value = "${currentWeather.humidity.value}",
                unit = currentWeather.humidity.unit.toSymbol(),
                description = stringResource(R.string.humidity_label)
            )
            WeatherParameter(
                icon = R.drawable.ic_precipitation,
                iconContentDescription = stringResource(R.string.precipitation_icon_content_description),
                value = "${currentWeather.precipitation.value}",
                unit = currentWeather.precipitation.unit.toSymbol(),
                description = stringResource(R.string.precipitation_label)
            )
        }
        Column {
            val hourlyListState = rememberLazyListState()
            val dailyListState = rememberLazyListState()
            val coroutineScope = rememberCoroutineScope()

            LazyRow(
                modifier = Modifier.padding(bottom = 16.dp),
                state = hourlyListState
            ) {
                coroutineScope.launch {
                    hourlyListState.animateScrollToItem(index = 0)
                }

                items(hourlyWeather.hourly) { weather ->
                    WeatherForecastCard(
                        topText = weather.time.format(DateTimeFormatter.ofPattern("HH:mm")),
                        iconRes = weather.condition.toIconRes(weather.isDay),
                        bottomText = "${weather.temperature.toInt()}${hourlyWeather.temperatureUnit.toSymbol()}"
                    )
                }
            }
            LazyRow(state = dailyListState) {
                coroutineScope.launch {
                    dailyListState.animateScrollToItem(index = 0)
                }

                items(dailyWeather.daily) { weather ->
                    WeatherForecastCard(
                        topText = weather.date.format(DateTimeFormatter.ofPattern("EE")),
                        iconRes = weather.condition.toIconRes(),
                        bottomText = "${weather.maxTemperature.toInt()}${dailyWeather.temperatureUnit.toSymbol()}\n" +
                                "${weather.minTemperature.toInt()}${dailyWeather.temperatureUnit.toSymbol()}"
                    )
                }
            }
        }
    }
}