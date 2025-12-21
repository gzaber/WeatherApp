package com.gzaber.weatherapp.ui.settings.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import com.gzaber.weatherapp.R
import com.gzaber.weatherapp.util.RobolectricTestRule
import com.gzaber.weatherapp.util.TestApplication
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class)
class SettingsContentTest {

    @get:Rule(order = 0)
    val robolectricTestRule = RobolectricTestRule()

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    private val context = ApplicationProvider.getApplicationContext<android.content.Context>()

    private val tempOptions = listOf("Celsius", "Fahrenheit")
    private val windOptions = listOf("km/h", "m/s")
    private val precipOptions = listOf("mm", "inch")

    @Test
    fun settingsContent_displaysAllElementsAndCorrectSelections() {
        with(composeTestRule) {
            setContent {
                SettingsContent(
                    contentPadding = PaddingValues(0.dp),
                    temperatureUnitOptions = tempOptions,
                    windSpeedUnitOptions = windOptions,
                    precipitationUnitOptions = precipOptions,
                    selectedTemperatureUnit = "Celsius",
                    selectedWindSpeedUnit = "km/h",
                    selectedPrecipitationUnit = "mm",
                    onTemperatureUnitSelected = {},
                    onWindSpeedUnitSelected = {},
                    onPrecipitationUnitSelected = {}
                )
            }

            onNodeWithText(context.getString(R.string.temperature_unit_title))
                .assertIsDisplayed()
            onNodeWithText(context.getString(R.string.wind_speed_unit_title))
                .assertIsDisplayed()
            onNodeWithText(context.getString(R.string.precipitation_unit_title))
                .assertIsDisplayed()

            onNodeWithText("Celsius").assertIsDisplayed()
            onNodeWithText("Fahrenheit").assertIsDisplayed()
            onNodeWithText("km/h").assertIsDisplayed()
            onNodeWithText("m/s").assertIsDisplayed()
            onNodeWithText("mm").assertIsDisplayed()
            onNodeWithText("inch").assertIsDisplayed()

            onNodeWithText("Celsius").assertIsSelected()
            onNodeWithText("km/h").assertIsSelected()
            onNodeWithText("mm").assertIsSelected()
        }
    }

    @Test
    fun onTemperatureUnitSelected_isCalled() {
        val onTemperatureUnitSelected: (String) -> Unit = mockk(relaxed = true)

        with(composeTestRule) {
            setContent {
                SettingsContent(
                    contentPadding = PaddingValues(0.dp),
                    temperatureUnitOptions = tempOptions,
                    windSpeedUnitOptions = windOptions,
                    precipitationUnitOptions = precipOptions,
                    selectedTemperatureUnit = "Celsius",
                    selectedWindSpeedUnit = "km/h",
                    selectedPrecipitationUnit = "mm",
                    onTemperatureUnitSelected = onTemperatureUnitSelected,
                    onWindSpeedUnitSelected = {},
                    onPrecipitationUnitSelected = {}
                )
            }

            onNodeWithText("Fahrenheit").performClick()
        }

        verify { onTemperatureUnitSelected("Fahrenheit") }
    }

    @Test
    fun onWindSpeedUnitSelected_isCalled() {
        val onWindSpeedUnitSelected: (String) -> Unit = mockk(relaxed = true)

        with(composeTestRule) {
            setContent {
                SettingsContent(
                    contentPadding = PaddingValues(0.dp),
                    temperatureUnitOptions = tempOptions,
                    windSpeedUnitOptions = windOptions,
                    precipitationUnitOptions = precipOptions,
                    selectedTemperatureUnit = "Celsius",
                    selectedWindSpeedUnit = "km/h",
                    selectedPrecipitationUnit = "mm",
                    onTemperatureUnitSelected = {},
                    onWindSpeedUnitSelected = onWindSpeedUnitSelected,
                    onPrecipitationUnitSelected = {}
                )
            }

            onNodeWithText("m/s").performClick()
        }

        verify { onWindSpeedUnitSelected("m/s") }
    }

    @Test
    fun onPrecipitationUnitSelected_isCalled() {
        val onPrecipitationUnitSelected: (String) -> Unit = mockk(relaxed = true)

        with(composeTestRule) {
            setContent {
                SettingsContent(
                    contentPadding = PaddingValues(0.dp),
                    temperatureUnitOptions = tempOptions,
                    windSpeedUnitOptions = windOptions,
                    precipitationUnitOptions = precipOptions,
                    selectedTemperatureUnit = "Celsius",
                    selectedWindSpeedUnit = "km/h",
                    selectedPrecipitationUnit = "mm",
                    onTemperatureUnitSelected = {},
                    onWindSpeedUnitSelected = {},
                    onPrecipitationUnitSelected = onPrecipitationUnitSelected
                )
            }

            onNodeWithText("inch").performClick()
        }

        verify { onPrecipitationUnitSelected("inch") }
    }
}
