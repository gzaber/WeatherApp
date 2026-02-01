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
class WeatherForecastCardTest {

    @get:Rule(order = 0)
    val robolectricTestRule = RobolectricTestRule()

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun weatherForecastCard_displaysAllElements() {
        val topText = "10:00"
        val bottomText = "15°C"
        val iconRes = R.drawable.ic_weather_clear_day

        with(composeTestRule) {
            setContent {
                WeatherForecastCard(
                    iconRes = iconRes,
                    topText = topText,
                    bottomText = bottomText
                )
            }

            onNodeWithText(topText).assertIsDisplayed()
            onNodeWithText(bottomText).assertIsDisplayed()
            onNodeWithContentDescription(context.getString(R.string.weather_condition_image_content_description))
                .assertIsDisplayed()
        }
    }
}
