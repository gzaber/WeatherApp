package com.gzaber.weatherapp.ui.settings.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
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
class RadioButtonGroupTest {

    @get:Rule(order = 0)
    val robolectricTestRule = RobolectricTestRule()

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    private val title = "Temperature"
    private val radioOptions = listOf("Celsius", "Fahrenheit")

    @Test
    fun radioButtonGroup_displaysAllElementsAndCorrectSelection() {
        val selectedOption = "Celsius"

        with(composeTestRule) {
            setContent {
                RadioButtonGroup(
                    title = title,
                    radioOptions = radioOptions,
                    selectedOption = selectedOption,
                    onOptionSelected = {}
                )
            }

            onNodeWithText(title).assertIsDisplayed()
            onNodeWithText("Celsius").assertIsDisplayed()
            onNodeWithText("Fahrenheit").assertIsDisplayed()

            onNodeWithText("Celsius").assertIsSelected()
        }
    }

    @Test
    fun radioButtonGroup_whenOptionClicked_callsOnOptionSelected() {
        val onOptionSelected: (String) -> Unit = mockk(relaxed = true)

        with(composeTestRule) {
            setContent {
                RadioButtonGroup(
                    title = title,
                    radioOptions = radioOptions,
                    selectedOption = "Celsius",
                    onOptionSelected = onOptionSelected
                )
            }

            onNodeWithText("Fahrenheit").performClick()
        }

        verify { onOptionSelected("Fahrenheit") }
    }
}
