package com.gzaber.weatherapp.ui.weather.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeather
import com.gzaber.weatherapp.data.repository.weather.model.CurrentWeatherParameter
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeather
import com.gzaber.weatherapp.data.repository.weather.model.DailyWeatherParameters
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeather
import com.gzaber.weatherapp.data.repository.weather.model.HourlyWeatherParameters
import com.gzaber.weatherapp.data.repository.weather.model.HumidityUnit
import com.gzaber.weatherapp.data.repository.weather.model.PrecipitationUnit
import com.gzaber.weatherapp.data.repository.weather.model.TemperatureUnit
import com.gzaber.weatherapp.data.repository.weather.model.WeatherCondition
import com.gzaber.weatherapp.data.repository.weather.model.WindSpeedUnit
import com.gzaber.weatherapp.util.RobolectricTestRule
import com.gzaber.weatherapp.util.TestApplication
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.time.LocalDate
import java.time.LocalDateTime

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class, qualifiers = "w1080dp-h2400dp-480dpi")
class WeatherContentTest {

    @get:Rule(order = 0)
    val robolectricTestRule = RobolectricTestRule()

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    val currentWeather = CurrentWeather(
        isDay = true,
        condition = WeatherCondition.CLEAR_SKY,
        temperature = CurrentWeatherParameter(TemperatureUnit.CELSIUS, 20.0),
        humidity = CurrentWeatherParameter(HumidityUnit.PERCENT, 50),
        windSpeed = CurrentWeatherParameter(WindSpeedUnit.KILOMETERS_PER_HOUR, 10.0),
        precipitation = CurrentWeatherParameter(PrecipitationUnit.MILLIMETER, 0.0)
    )
    val hourlyWeather = HourlyWeather(
        temperatureUnit = TemperatureUnit.CELSIUS,
        hourly = listOf(
            HourlyWeatherParameters(
                time = LocalDateTime.now(),
                isDay = true,
                condition = WeatherCondition.CLEAR_SKY,
                temperature = 22.0
            )
        )
    )
    val dailyWeather = DailyWeather(
        temperatureUnit = TemperatureUnit.CELSIUS,
        daily = listOf(
            DailyWeatherParameters(
                date = LocalDate.now(),
                condition = WeatherCondition.CLEAR_SKY,
                minTemperature = 15.0,
                maxTemperature = 25.0
            )
        )
    )

    @Test
    fun weatherContent_displaysAllData() {
        with(composeTestRule) {
            setContent {
                WeatherContent(
                    contentPadding = PaddingValues(0.dp),
                    currentWeather = currentWeather,
                    hourlyWeather = hourlyWeather,
                    dailyWeather = dailyWeather
                )
            }

            onNodeWithText("20°C").assertIsDisplayed()
            onNodeWithText("Clear sky").assertIsDisplayed()
            onNodeWithText("10.0 km/h").assertIsDisplayed()
            onNodeWithText("50 %").assertIsDisplayed()
            onNodeWithText("0.0 mm").assertIsDisplayed()
            onNodeWithText("22°C").assertIsDisplayed()
            onNodeWithText("25°C\n15°C").assertIsDisplayed()
        }
    }
}
