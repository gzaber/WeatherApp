package com.gzaber.weatherapp.ui.settings

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.gzaber.weatherapp.R
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
class SettingsScreenTest {

    @get:Rule(order = 0)
    val robolectricTestRule = RobolectricTestRule()

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    private val mockViewModel: SettingsViewModel = mockk(relaxed = true)
    private val onNavigateBack: () -> Unit = mockk(relaxed = true)
    private val context = RuntimeEnvironment.getApplication()

    @Test
    fun settingsScreen_displaysLoadingIndicator_whenStateIsLoading() {
        val uiState = SettingsUiState(settingsDataState = SettingsDataState.Loading)
        every { mockViewModel.uiState } returns MutableStateFlow(uiState)

        with(composeTestRule) {
            setContent {
                SettingsScreen(onNavigateBack = onNavigateBack, viewModel = mockViewModel)
            }

            onNodeWithContentDescription(
                context.getString(R.string.loading_indicator_content_description)
            ).assertIsDisplayed()
        }
    }

    @Test
    fun settingsScreen_displaysContent_whenStateIsSuccess() {
        val uiState = SettingsUiState(settingsDataState = SettingsDataState.Success(mockk()))
        every { mockViewModel.uiState } returns MutableStateFlow(uiState)

        with(composeTestRule) {
            setContent {
                SettingsScreen(onNavigateBack = onNavigateBack, viewModel = mockViewModel)
            }

            onNodeWithText(
                context.getString(R.string.temperature_unit_title)
            ).assertIsDisplayed()
            onNodeWithText(
                context.getString(R.string.wind_speed_unit_title)
            ).assertIsDisplayed()
            onNodeWithText(
                context.getString(R.string.precipitation_unit_title)
            ).assertIsDisplayed()
        }
    }

    @Test
    fun settingsScreen_displaysErrors_whenStateIsError() {
        val uiState = SettingsUiState(settingsDataState = SettingsDataState.Error)
        every { mockViewModel.uiState } returns MutableStateFlow(uiState)

        with(composeTestRule) {
            setContent {
                SettingsScreen(onNavigateBack = onNavigateBack, viewModel = mockViewModel)
            }

            onNodeWithText(
                context.getString(R.string.unable_to_load_settings_error)
            ).assertIsDisplayed()
            onNodeWithText(
                context.getString(R.string.generic_error_message)
            ).assertIsDisplayed()
        }
    }

    @Test
    fun topBar_backIconIsClickable() {
        val uiState = SettingsUiState(settingsDataState = SettingsDataState.Success(mockk()))
        every { mockViewModel.uiState } returns MutableStateFlow(uiState)

        with(composeTestRule) {
            setContent {
                SettingsScreen(onNavigateBack = onNavigateBack, viewModel = mockViewModel)
            }

            onNodeWithContentDescription(
                context.getString(R.string.navigate_back_content_description)
            ).performClick()
        }
        verify { onNavigateBack() }
    }
}
