package com.gzaber.weatherapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.gzaber.weatherapp.data.repository.locations.LocationsRepository
import com.gzaber.weatherapp.data.repository.locations.model.Location
import com.gzaber.weatherapp.data.repository.userpreferences.UserPreferencesRepository
import com.gzaber.weatherapp.data.repository.weather.WeatherRepository
import com.gzaber.weatherapp.di.viewModelModule
import com.gzaber.weatherapp.util.KoinTestRule
import com.gzaber.weatherapp.util.RobolectricTestRule
import com.gzaber.weatherapp.util.TestApplication
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class)
class WeatherNavigationTest : KoinTest {

    private val context = RuntimeEnvironment.getApplication()
    private val mockWeatherRepository = mockk<WeatherRepository>(relaxed = true)
    private val mockLocationsRepository = mockk<LocationsRepository>(relaxed = true)
    private val mockUserPreferencesRepository = mockk<UserPreferencesRepository>(relaxed = true)


    @get:Rule(order = 0)
    val robolectricTestRule = RobolectricTestRule()

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    @get:Rule(order = 2)
    val koinTestRule = KoinTestRule(
        modules = listOf(
            viewModelModule,
            module {
                single { mockWeatherRepository }
                single { mockUserPreferencesRepository }
                single { mockLocationsRepository }
            }
        )
    )

    @Before
    fun setUp() {
        composeTestRule.setContent { WeatherNavigation() }
    }

    @Test
    fun weatherScreen_settingsIconClicked_navigatesToSettingsScreen() {
        with(composeTestRule) {
            onNodeWithContentDescription(context.getString(R.string.settings_screen_content_description))
                .assertIsDisplayed()
                .performClick()
            onNodeWithText(context.getString(R.string.settings_screen_title))
        }
    }

    @Test
    fun settingsScreen_backButtonClicked_navigatesBackToWeatherScreen() {
        with(composeTestRule) {
            onNodeWithContentDescription(context.getString(R.string.settings_screen_content_description))
                .assertIsDisplayed()
                .performClick()
            onNodeWithText(context.getString(R.string.settings_screen_title))
                .assertIsDisplayed()
            onNodeWithContentDescription(context.getString(R.string.navigate_back_content_description))
                .assertIsDisplayed()
                .performClick()
            onNodeWithContentDescription(context.getString(R.string.settings_screen_content_description))
                .assertIsDisplayed()
            onNodeWithContentDescription(context.getString(R.string.search_screen_content_description))
                .assertIsDisplayed()
        }
    }

    @Test
    fun weatherScreen_searchIconClicked_navigatesToSearchScreen() {
        with(composeTestRule) {
            onNodeWithContentDescription(context.getString(R.string.search_screen_content_description))
                .assertIsDisplayed()
                .performClick()
            onNodeWithText(context.getString(R.string.search_screen_title))
        }
    }

    @Test
    fun searchScreen_backButtonClicked_navigatesBackToWeatherScreen() {
        with(composeTestRule) {
            onNodeWithContentDescription(context.getString(R.string.search_screen_content_description))
                .assertIsDisplayed()
                .performClick()
            onNodeWithText(context.getString(R.string.search_screen_title))
                .assertIsDisplayed()
            onNodeWithContentDescription(context.getString(R.string.navigate_back_content_description))
                .assertIsDisplayed()
                .performClick()
            onNodeWithContentDescription(context.getString(R.string.settings_screen_content_description))
                .assertIsDisplayed()
            onNodeWithContentDescription(context.getString(R.string.search_screen_content_description))
                .assertIsDisplayed()
        }
    }

    @Test
    fun searchScreen_locationCardClicked_navigatesBackToWeatherScreen() {
        every { mockLocationsRepository.observeAll() } returns flowOf(
            listOf(
                Location("1", 52.23, 21.01, "Warsaw", "Poland")
            )
        )

        with(composeTestRule) {
            onNodeWithContentDescription(context.getString(R.string.search_screen_content_description))
                .assertIsDisplayed()
                .performClick()
            onNodeWithText(context.getString(R.string.search_screen_title))
                .assertIsDisplayed()
            onNodeWithText("Warsaw")
                .assertIsDisplayed()
                .performClick()
            onNodeWithContentDescription(context.getString(R.string.search_screen_content_description))
                .assertIsDisplayed()
        }
    }
}