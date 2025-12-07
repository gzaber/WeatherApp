package com.gzaber.weatherapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gzaber.weatherapp.data.repository.locations.LocationsRepository
import com.gzaber.weatherapp.data.repository.locations.model.Location
import com.gzaber.weatherapp.data.repository.userpreferences.UserPreferencesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val locationsRepository: LocationsRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    private var searchJob: Job? = null

    init {
        observeLocationHistory()
    }

    fun onSearchTextChanged(query: String) {
        _uiState.update {
            it.copy(query = query)
        }
        searchJob?.cancel()
        if (query.isNotBlank()) {
            searchJob = viewModelScope.launch {
                delay(300) // Debounce
                searchLocation(query)
            }
        } else {
            _uiState.update {
                it.copy(searchState = SearchState.Empty)
            }
        }
    }

    fun onSearchTextCleared() {
        searchJob?.cancel()
        _uiState.update {
            it.copy(
                query = "",
                searchState = SearchState.Empty
            )
        }
    }

    fun selectLocation(location: Location) {
        saveToSettings(location)
        saveToLocationHistory(location)
    }

    fun removeFromLocationHistory(location: Location) {
        viewModelScope.launch {
            try {
                locationsRepository.delete(location)
            } catch (_: Throwable) {
                _uiState.update { it.copy(searchState = SearchState.Error) }
            }
        }
    }

    private fun observeLocationHistory() {
        viewModelScope.launch {
            locationsRepository.observeAll()
                .catch {
                    _uiState.update { it.copy(searchState = SearchState.Error) }
                }
                .collect { locations ->
                    _uiState.update {
                        it.copy(
                            savedLocations = locations.reversed()
                        )
                    }
                }
        }
    }

    private suspend fun searchLocation(query: String) {
        _uiState.update { it.copy(searchState = SearchState.Loading) }
        try {
            val searchResults = locationsRepository.search(query)
            _uiState.update {
                it.copy(
                    searchState = SearchState.Success(searchResults)
                )
            }
        } catch (_: Throwable) {
            _uiState.update { it.copy(searchState = SearchState.Error) }
        }
    }

    private fun saveToSettings(location: Location) {
        viewModelScope.launch {
            try {
                userPreferencesRepository.updateLocation(
                    latitude = location.latitude,
                    longitude = location.longitude,
                    name = location.name,
                    country = location.country
                )
            } catch (_: Throwable) {
                _uiState.update { it.copy(searchState = SearchState.Error) }
            }
        }
    }

    private fun saveToLocationHistory(location: Location) {
        viewModelScope.launch {
            try {
                locationsRepository.insert(location)
            } catch (_: Throwable) {
                _uiState.update { it.copy(searchState = SearchState.Error) }
            }
        }
    }
}
