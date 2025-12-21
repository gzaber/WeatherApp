package com.gzaber.weatherapp.ui.search.composable

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import com.gzaber.weatherapp.R
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
class LocationListItemTest {

    @get:Rule(order = 0)
    val robolectricTestRule = RobolectricTestRule()

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    private val location = Location(
        id = "1",
        name = "Warsaw",
        country = "Poland",
        latitude = 52.23,
        longitude = 21.01
    )

    @Test
    fun locationListItem_displaysAllElements() {
        with(composeTestRule) {
            setContent {
                LocationListItem(location = location, onLocationClick = {})
            }

            onNodeWithText("Warsaw").assertIsDisplayed()
            onNodeWithText("Poland").assertIsDisplayed()

            val context = ApplicationProvider.getApplicationContext<Context>()
            val contentDescription = context.getString(R.string.location_icon_content_description)
            onNodeWithContentDescription(contentDescription).assertIsDisplayed()
        }
    }

    @Test
    fun locationListItem_whenClicked_callsOnLocationClick() {
        val onLocationClick: (Location) -> Unit = mockk(relaxed = true)

        with(composeTestRule) {
            setContent {
                LocationListItem(location = location, onLocationClick = onLocationClick)
            }

            onNodeWithText("Warsaw").performClick()
        }

        verify { onLocationClick(location) }
    }
}
