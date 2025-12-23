package com.gzaber.weatherapp.ui.weather

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.gzaber.weatherapp.R
import com.gzaber.weatherapp.data.repository.userpreferences.model.LocationPreferences
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
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.time.LocalDate
import java.time.LocalDateTime

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class)
class WeatherScreenTest {

    @get:Rule(order = 0)
    val robolectricTestRule = RobolectricTestRule()

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    private val mockViewModel: WeatherViewModel = mockk(relaxed = true)
    private val onNavigateToSettings: () -> Unit = mockk(relaxed = true)
    private val onNavigateToSearch: () -> Unit = mockk(relaxed = true)
    private val context = RuntimeEnvironment.getApplication()

    private val currentWeather = CurrentWeather(
        isDay = true,
        condition = WeatherCondition.CLEAR_SKY,
        temperature = CurrentWeatherParameter(TemperatureUnit.CELSIUS, 20.0),
        humidity = CurrentWeatherParameter(HumidityUnit.PERCENT, 50),
        windSpeed = CurrentWeatherParameter(WindSpeedUnit.KILOMETERS_PER_HOUR, 10.0),
        precipitation = CurrentWeatherParameter(PrecipitationUnit.MILLIMETER, 0.0)
    )
    private val hourlyWeather = HourlyWeather(
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
    private val dailyWeather = DailyWeather(
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
    fun weatherScreen_displaysLoadingIndicator_whenStateIsLoading() {
        val uiState = WeatherUiState(weatherDataState = WeatherDataState.Loading)
        every { mockViewModel.uiState } returns MutableStateFlow(uiState)

        with(composeTestRule) {
            setContent {
                WeatherScreen(
                    onNavigateToSettings = onNavigateToSettings,
                    onNavigateToSearch = onNavigateToSearch,
                    viewModel = mockViewModel
                )
            }

            onNodeWithContentDescription(context.getString(R.string.loading_indicator_content_description))
                .assertIsDisplayed()
        }
    }

    @Test
    fun weatherScreen_displaysWeatherContent_whenStateIsSuccess() {
        val location = LocationPreferences(52.23, 21.01, "Warsaw", "Poland")
        val uiState = WeatherUiState(
            weatherDataState = WeatherDataState.Success(
                currentWeather = currentWeather,
                hourlyWeather = hourlyWeather,
                dailyWeather = dailyWeather
            ),
            locationPreferences = location
        )
        every { mockViewModel.uiState } returns MutableStateFlow(uiState)

        with(composeTestRule) {
            setContent {
                WeatherScreen(
                    onNavigateToSettings = onNavigateToSettings,
                    onNavigateToSearch = onNavigateToSearch,
                    viewModel = mockViewModel
                )
            }

            onNodeWithText("Warsaw, Poland").assertIsDisplayed()
            onNodeWithText(context.getString(R.string.weather_now_label))
                .assertIsDisplayed()
        }
    }

    @Test
    fun weatherScreen_displaysErrors_whenStateIsError() {
        val uiState = WeatherUiState(weatherDataState = WeatherDataState.Error)
        every { mockViewModel.uiState } returns MutableStateFlow(uiState)

        with(composeTestRule) {
            setContent {
                WeatherScreen(
                    onNavigateToSettings = onNavigateToSettings,
                    onNavigateToSearch = onNavigateToSearch,
                    viewModel = mockViewModel
                )
            }

            onNodeWithText(context.getString(R.string.weather_data_fetch_error))
                .assertIsDisplayed()
            onNodeWithText(context.getString(R.string.generic_error_message))
                .assertIsDisplayed()
        }
    }

    @Test
    fun topBarIcons_areClickable() {
        val location = LocationPreferences(52.23, 21.01, "Warsaw", "Poland")
        val uiState = WeatherUiState(
            weatherDataState = WeatherDataState.Success(
                currentWeather = currentWeather,
                hourlyWeather = hourlyWeather,
                dailyWeather = dailyWeather
            ),
            locationPreferences = location
        )
        every { mockViewModel.uiState } returns MutableStateFlow(uiState)

        with(composeTestRule) {
            setContent {
                WeatherScreen(
                    onNavigateToSettings = onNavigateToSettings,
                    onNavigateToSearch = onNavigateToSearch,
                    viewModel = mockViewModel
                )
            }

            onNodeWithContentDescription(context.getString(R.string.settings_screen_content_description)).performClick()
            verify { onNavigateToSettings() }

            onNodeWithContentDescription(
                context.getString(R.string.search_screen_content_description)
            ).performClick()
            verify { onNavigateToSearch() }
        }
    }
}
