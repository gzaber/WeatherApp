package com.gzaber.weatherapp.ui.weather.composable

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import com.gzaber.weatherapp.R
import com.gzaber.weatherapp.util.RobolectricTestRule
import com.gzaber.weatherapp.util.TestApplication
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class)
class WeatherParameterTest {

    @get:Rule(order = 0)
    val robolectricTestRule = RobolectricTestRule()

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun weatherParameter_displaysAllElements() {
        val value = "10.0"
        val unit = "km/h"
        val description = "Wind"
        val iconRes = R.drawable.ic_wind
        val iconContentDescription =
            context.getString(R.string.wind_speed_icon_content_description)

        with(composeTestRule) {
            setContent {
                WeatherParameter(
                    icon = iconRes,
                    iconContentDescription = iconContentDescription,
                    value = value,
                    unit = unit,
                    description = description
                )
            }

            composeTestRule.onNodeWithText("$value $unit").assertIsDisplayed()
            composeTestRule.onNodeWithText(description).assertIsDisplayed()
            composeTestRule.onNodeWithContentDescription(iconContentDescription).assertIsDisplayed()
        }
    }
}
