package com.gzaber.weatherapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.gzaber.weatherapp.di.dataModule
import com.gzaber.weatherapp.di.viewModelModule
import com.gzaber.weatherapp.util.KoinTestRule
import com.gzaber.weatherapp.util.RobolectricTestRule
import com.gzaber.weatherapp.util.TestApplication
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class)
class WeatherActivityTest : KoinTest {

    private val context = RuntimeEnvironment.getApplication()

    @get:Rule(order = 0)
    val robolectricTestRule = RobolectricTestRule()

    @get:Rule(order = 1)
    val koinTestRule = KoinTestRule(
        modules = listOf(dataModule, viewModelModule)
    )

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<WeatherActivity>()

    @Test
    fun activity_displaysAppScreenWithNavigationButtons() {

        composeTestRule.onNodeWithContentDescription(context.getString(R.string.settings_screen_content_description))
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(context.getString(R.string.search_screen_content_description))
            .assertIsDisplayed()
    }
}