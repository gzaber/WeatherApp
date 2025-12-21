package com.gzaber.weatherapp.ui.search.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.gzaber.weatherapp.data.repository.locations.model.Location
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
class LocationListTest {

    @get:Rule(order = 0)
    val robolectricTestRule = RobolectricTestRule()

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    private val locations = listOf(
        Location(
            id = "1",
            name = "Warsaw",
            country = "Poland",
            latitude = 52.23,
            longitude = 21.01
        ),
        Location(
            id = "2",
            name = "London",
            country = "United Kingdom",
            latitude = 51.51,
            longitude = -0.13
        )
    )

    @Test
    fun locationList_displaysLocationItems() {
        with(composeTestRule) {
            setContent {
                LocationList(locations = locations, onLocationClick = {})
            }

            onNodeWithText("Warsaw").assertIsDisplayed()
            onNodeWithText("Poland").assertIsDisplayed()
            onNodeWithText("London").assertIsDisplayed()
            onNodeWithText("United Kingdom").assertIsDisplayed()
        }
    }

    @Test
    fun locationList_withSwipe_displaysSwipeableLocationItems() {
        with(composeTestRule) {
            setContent {
                LocationList(locations = locations, onLocationClick = {}, onLocationSwipe = {})
            }

            onNodeWithText("Warsaw").assertIsDisplayed()
            onNodeWithText("Poland").assertIsDisplayed()
            onNodeWithText("London").assertIsDisplayed()
            onNodeWithText("United Kingdom").assertIsDisplayed()
        }
    }

    @Test
    fun locationList_onLocationClick_isCalled() {
        val onLocationClick: (Location) -> Unit = mockk(relaxed = true)
        with(composeTestRule) {
            setContent {
                LocationList(locations = locations, onLocationClick = onLocationClick)
            }

            onNodeWithText("Warsaw").performClick()

            verify { onLocationClick(locations.first()) }
        }
    }
}
