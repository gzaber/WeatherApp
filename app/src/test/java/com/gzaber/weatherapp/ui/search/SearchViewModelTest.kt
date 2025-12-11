package com.gzaber.weatherapp.ui.search

import com.gzaber.weatherapp.data.repository.locations.LocationsRepository
import com.gzaber.weatherapp.data.repository.locations.model.Location
import com.gzaber.weatherapp.data.repository.userpreferences.UserPreferencesRepository
import com.gzaber.weatherapp.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var locationsRepository: LocationsRepository
    private lateinit var userPreferencesRepository: UserPreferencesRepository
    private lateinit var viewModel: SearchViewModel

    val location = Location("1", 5.0, 15.0, "Location", "Country")
    val history = listOf(
        Location("2", 10.0, 20.0, "Location1", "Country1"),
        Location("3", 30.0, 40.0, "Location2", "Country2")
    )

    @Before
    fun setUp() {
        locationsRepository = mockk(relaxed = true)
        userPreferencesRepository = mockk(relaxed = true)

        every { locationsRepository.observeAll() } returns flowOf(history)
    }

    @Test
    fun init_emitsEmptyState() = runTest {
        every { locationsRepository.observeAll() } returns flowOf(emptyList())

        viewModel = SearchViewModel(locationsRepository, userPreferencesRepository)

        assertEquals(SearchState.Empty, viewModel.uiState.value.searchState)
        assertTrue(viewModel.uiState.value.query.isEmpty())
        assertTrue(viewModel.uiState.value.savedLocations.isEmpty())
    }

    @Test
    fun init_updatesStateWithLocations() = runTest {
        viewModel = SearchViewModel(locationsRepository, userPreferencesRepository)

        assertEquals(history.reversed(), viewModel.uiState.value.savedLocations)
    }

    @Test
    fun init_repositoryThrows_emitsErrorState() = runTest {
        every { locationsRepository.observeAll() } returns flowOf(history).onStart { throw Exception() }

        viewModel = SearchViewModel(locationsRepository, userPreferencesRepository)

        assertEquals(SearchState.Error, viewModel.uiState.value.searchState)
    }

    @Test
    fun onSearchTextChanged_updatesQuery_emitsSuccessStateWithResults() = runTest {
        val searchResults = listOf(location)
        coEvery { locationsRepository.search("query") } returns searchResults

        viewModel = SearchViewModel(locationsRepository, userPreferencesRepository)
        viewModel.onSearchTextChanged("query")

        advanceUntilIdle()

        assertEquals("query", viewModel.uiState.value.query)
        assertEquals(SearchState.Success(searchResults), viewModel.uiState.value.searchState)
    }

    @Test
    fun onSearchTextChanged_repositoryThrows_emitsErrorState() = runTest {
        coEvery { locationsRepository.search(any()) } throws Exception()

        viewModel = SearchViewModel(locationsRepository, userPreferencesRepository)
        viewModel.onSearchTextChanged("query")

        advanceUntilIdle()

        assertEquals(SearchState.Error, viewModel.uiState.value.searchState)
    }

    @Test
    fun onSearchTextChanged_whenEmpty_emitsEmptyState() = runTest {
        viewModel = SearchViewModel(locationsRepository, userPreferencesRepository)

        viewModel.onSearchTextChanged("query")
        viewModel.onSearchTextChanged("")

        assertEquals(SearchState.Empty, viewModel.uiState.value.searchState)
        assertTrue(viewModel.uiState.value.query.isEmpty())
    }

    @Test
    fun onSearchTextCleared_clearsQuery() = runTest {
        viewModel = SearchViewModel(locationsRepository, userPreferencesRepository)

        viewModel.onSearchTextChanged("query")
        viewModel.onSearchTextCleared()

        assertTrue(viewModel.uiState.value.query.isEmpty())
    }

    @Test
    fun selectLocation_savesToSettingsAndHistory() = runTest {
        viewModel = SearchViewModel(locationsRepository, userPreferencesRepository)

        viewModel.selectLocation(location)

        coVerify {
            userPreferencesRepository.updateLocation(
                location.latitude,
                location.longitude,
                location.name,
                location.country
            )
            locationsRepository.insert(location)
        }
    }

    @Test
    fun selectLocation_userPreferencesRepositoryThrows_emitsErrorState() = runTest {
        coEvery {
            userPreferencesRepository.updateLocation(
                any(),
                any(),
                any(),
                any()
            )
        } throws Exception()

        viewModel = SearchViewModel(locationsRepository, userPreferencesRepository)
        viewModel.selectLocation(location)

        assertEquals(SearchState.Error, viewModel.uiState.value.searchState)
    }

    @Test
    fun selectLocation_locationsRepositoryThrows_emitsErrorState() = runTest {
        coEvery {
            locationsRepository.insert(any())
        } throws Exception()

        viewModel = SearchViewModel(locationsRepository, userPreferencesRepository)
        viewModel.selectLocation(location)

        assertEquals(SearchState.Error, viewModel.uiState.value.searchState)
    }

    @Test
    fun removeFromLocationHistory_deletesLocation() = runTest {
        viewModel = SearchViewModel(locationsRepository, userPreferencesRepository)

        viewModel.removeFromLocationHistory(location)

        coVerify { locationsRepository.delete(location) }
    }

    @Test
    fun removeFromLocationHistory_repositoryThrows_emitsErrorState() = runTest {
        coEvery { locationsRepository.delete(any()) } throws Exception()

        viewModel = SearchViewModel(locationsRepository, userPreferencesRepository)
        viewModel.removeFromLocationHistory(location)

        assertEquals(SearchState.Error, viewModel.uiState.value.searchState)
    }
}
