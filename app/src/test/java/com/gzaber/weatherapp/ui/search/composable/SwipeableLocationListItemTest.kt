package com.gzaber.weatherapp.ui.search.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeRight
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
class SwipeableLocationListItemTest {

    @get:Rule(order = 0)
    val robolectricTestRule = RobolectricTestRule()

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    private val location =
        Location(id = "1", name = "Warsaw", country = "Poland", latitude = 52.23, longitude = 21.01)

    @Test
    fun displaysContentAndHandlesClick() {
        val onLocationClick: (Location) -> Unit = mockk(relaxed = true)

        with(composeTestRule) {
            setContent {
                SwipeableLocationListItem(
                    location = location,
                    onLocationClick = onLocationClick,
                    onLocationSwipe = {}
                )
            }

            onNodeWithText("Warsaw").assertIsDisplayed()
            onNodeWithText("Poland").assertIsDisplayed()
            onNodeWithText("Warsaw").performClick()
        }

        verify { onLocationClick(location) }
    }

    @Test
    fun whenSwiped_callsOnLocationSwipe() {
        val onLocationSwipe: (Location) -> Unit = mockk(relaxed = true)

        with(composeTestRule) {
            setContent {
                SwipeableLocationListItem(
                    location = location,
                    onLocationClick = {},
                    onLocationSwipe = onLocationSwipe
                )
            }

            onNodeWithText("Warsaw").performTouchInput { swipeRight() }
        }

        verify { onLocationSwipe(location) }
    }
}
