package com.gzaber.weatherapp.ui.search.composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
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
class SearchBarTest {

    @get:Rule(order = 0)
    val robolectricTestRule = RobolectricTestRule()

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    private val context = ApplicationProvider.getApplicationContext<android.content.Context>()

    @Test
    fun searchBar_displaysInitialTextAndIcons() {
        val searchText = "Warsaw"
        with(composeTestRule) {
            setContent {
                SearchBar(
                    searchText = searchText,
                    onSearchTextChanged = {},
                    onSearchTextCleared = {}
                )
            }

            onNodeWithText(searchText).assertIsDisplayed()
            onNodeWithContentDescription(
                context.getString(R.string.search_icon_content_description)
            ).assertIsDisplayed()
            onNodeWithContentDescription(
                context.getString(R.string.clear_search_text_content_description)
            ).assertIsDisplayed()
        }
    }

    @Test
    fun searchBar_placeholderIsDisplayed_whenSearchTextIsEmpty() {
        with(composeTestRule)
        {
            setContent {
                SearchBar(
                    searchText = "",
                    onSearchTextChanged = {},
                    onSearchTextCleared = {}
                )
            }

            onNodeWithText(
                context.getString(R.string.search_placeholder)
            ).assertIsDisplayed()
        }
    }

    @Test
    fun searchBar_onClearClicked_callsOnSearchTextCleared() {
        val onSearchTextCleared: () -> Unit = mockk(relaxed = true)

        with(composeTestRule) {
            setContent {
                SearchBar(
                    searchText = "some text",
                    onSearchTextChanged = {},
                    onSearchTextCleared = onSearchTextCleared
                )
            }

            onNodeWithContentDescription(
                context.getString(R.string.clear_search_text_content_description)
            ).performClick()
        }

        verify { onSearchTextCleared() }
    }

    @Test
    fun searchBar_onTextChanged_callsOnSearchTextChanged() {
        val onSearchTextChanged: (String) -> Unit = mockk(relaxed = true)
        val newText = "new query"

        with(composeTestRule) {
            setContent {
                var text by remember { mutableStateOf("") }
                SearchBar(
                    searchText = text,
                    onSearchTextChanged = {
                        onSearchTextChanged(it)
                    },
                    onSearchTextCleared = {}
                )
            }

            onNodeWithText(context.getString(R.string.search_placeholder))
                .performTextInput(newText)
        }

        verify { onSearchTextChanged(newText) }
    }
}
