package com.gzaber.weatherapp.ui.search

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeRight
import com.gzaber.weatherapp.R
import com.gzaber.weatherapp.data.repository.locations.model.Location
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

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class)
class SearchScreenTest {

    @get:Rule(order = 0)
    val robolectricTestRule = RobolectricTestRule()

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    private val mockViewModel: SearchViewModel = mockk(relaxed = true)
    private val onNavigateBack: () -> Unit = mockk(relaxed = true)
    private val context = RuntimeEnvironment.getApplication()

    @Test
    fun searchScreen_displaysSavedLocations_whenQueryIsBlank() {
        val savedLocations = listOf(Location("1", 52.23, 21.01, "Warsaw", "Poland"))
        val uiState = SearchUiState(query = "", savedLocations = savedLocations)
        every { mockViewModel.uiState } returns MutableStateFlow(uiState)

        with(composeTestRule) {
            setContent {
                SearchScreen(
                    onNavigateBack = onNavigateBack,
                    viewModel = mockViewModel
                )
            }

            onNodeWithText(context.getString(R.string.recent_searches_title)).assertIsDisplayed()
            onNodeWithText("Warsaw").assertIsDisplayed()
            onNodeWithText("Poland").assertIsDisplayed()
        }
    }

    @Test
    fun savedLocationItem_swipe_callsViewModel() {
        val location = Location("1", 52.23, 21.01, "Warsaw", "Poland")
        val savedLocations = listOf(location)
        val uiState = SearchUiState(query = "", savedLocations = savedLocations)
        every { mockViewModel.uiState } returns MutableStateFlow(uiState)

        with(composeTestRule) {
            setContent {
                SearchScreen(
                    onNavigateBack = onNavigateBack,
                    viewModel = mockViewModel
                )
            }

            onNodeWithText("Warsaw").performTouchInput { swipeRight() }
        }

        verify { mockViewModel.removeFromLocationHistory(location) }
    }

    @Test
    fun searchScreen_displaysLoading_whenStateIsLoading() {
        val uiState = SearchUiState(query = "some query", searchState = SearchState.Loading)
        every { mockViewModel.uiState } returns MutableStateFlow(uiState)

        with(composeTestRule) {
            setContent {
                SearchScreen(
                    onNavigateBack = onNavigateBack,
                    viewModel = mockViewModel
                )
            }

            onNodeWithContentDescription(
                context.getString(R.string.loading_indicator_content_description)
            ).assertIsDisplayed()
        }
    }

    @Test
    fun searchScreen_displaysSuccess_whenStateIsSuccess() {
        val locations = listOf(
            Location("1", 52.23, 21.01, "Warsaw", "Poland")
        )
        val uiState = SearchUiState(query = "warsaw", searchState = SearchState.Success(locations))
        every { mockViewModel.uiState } returns MutableStateFlow(uiState)

        with(composeTestRule) {
            setContent {
                SearchScreen(
                    onNavigateBack = onNavigateBack,
                    viewModel = mockViewModel
                )
            }

            onNodeWithText("Warsaw").assertIsDisplayed()
            onNodeWithText("Poland").assertIsDisplayed()
        }
    }

    @Test
    fun searchScreen_displaysEmpty_whenStateIsEmpty() {
        val uiState = SearchUiState(query = "nonexistent", searchState = SearchState.Empty)
        every { mockViewModel.uiState } returns MutableStateFlow(uiState)

        with(composeTestRule) {
            setContent {
                SearchScreen(
                    onNavigateBack = onNavigateBack,
                    viewModel = mockViewModel
                )
            }

            onNodeWithText(
                context.getString(R.string.search_placeholder)
            ).assertIsNotDisplayed()
        }
    }

    @Test
    fun searchScreen_displaysError_whenStateIsError() {
        val uiState = SearchUiState(query = "error", searchState = SearchState.Error)
        every { mockViewModel.uiState } returns MutableStateFlow(uiState)

        with(composeTestRule) {
            setContent {
                SearchScreen(
                    onNavigateBack = onNavigateBack,
                    viewModel = mockViewModel
                )
            }

            onNodeWithText(
                context.getString(R.string.unable_to_load_location_data_error)
            ).assertIsDisplayed()
            onNodeWithText(
                context.getString(R.string.generic_error_message)
            ).assertIsDisplayed()
        }
    }

    @Test
    fun topBar_backIconIsClickable() {
        every { mockViewModel.uiState } returns MutableStateFlow(SearchUiState())

        with(composeTestRule) {
            setContent {
                SearchScreen(
                    onNavigateBack = onNavigateBack,
                    viewModel = mockViewModel
                )
            }

            onNodeWithContentDescription(
                context.getString(R.string.navigate_back_content_description)
            ).performClick()
        }

        verify { onNavigateBack() }
    }

    @Test
    fun locationList_itemClick_callsViewModelAndNavigates() {
        val location = Location("1", 52.23, 21.01, "Warsaw", "Poland")
        val locations = listOf(location)
        val uiState = SearchUiState(query = "warsaw", searchState = SearchState.Success(locations))
        every { mockViewModel.uiState } returns MutableStateFlow(uiState)

        with(composeTestRule) {
            setContent {
                SearchScreen(
                    onNavigateBack = onNavigateBack,
                    viewModel = mockViewModel
                )
            }

            onNodeWithText("Warsaw").performClick()
        }

        verify { mockViewModel.selectLocation(location) }
        verify { onNavigateBack() }
    }
}
