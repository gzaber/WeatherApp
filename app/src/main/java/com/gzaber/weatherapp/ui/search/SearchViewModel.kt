package com.gzaber.weatherapp.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gzaber.weatherapp.data.repository.locations.LocationsRepository
import com.gzaber.weatherapp.data.repository.locations.model.Location
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val locationsRepository: LocationsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeLocationHistory()
    }

    fun onSearchTextChanged(query: String) {
        _uiState.update {
            it.copy(searchText = query)
        }
        if (query.isNotBlank()) {
            searchLocation(query)
        } else {
            _uiState.update {
                it.copy(searchResults = listOf())
            }
        }
    }

    fun onSearchTextCleared() {
        _uiState.update {
            it.copy(
                searchText = "",
                searchResults = listOf()
            )
        }
    }

    fun saveToLocationHistory(location: Location) {
        viewModelScope.launch {
            try {
                locationsRepository.insert(location)
            } catch (_: Throwable) {
                _uiState.update { it.copy(isError = true) }
            }
        }
    }

    fun removeFromLocationHistory(location: Location) {
        viewModelScope.launch {
            try {
                locationsRepository.delete(location)
            } catch (_: Throwable) {
                _uiState.update { it.copy(isError = true) }
            }
        }
    }

    private fun observeLocationHistory() {
        _uiState.update { it.copy(isLoadingLocationHistory = true) }
        viewModelScope.launch {
            locationsRepository.observeAll()
                .catch { e ->
                    _uiState.update {
                        it.copy(isError = true)
                    }
                    Log.e("API", "observeLocationHistory: $e")
                }
                .collect { locations ->
                    _uiState.update {
                        it.copy(
                            locationHistory = locations,
                            isLoadingLocationHistory = false
                        )
                    }
                }
        }
    }

    private fun searchLocation(query: String) {
        _uiState.update { it.copy(isLoadingSearchResults = true) }
        viewModelScope.launch {
            try {
                val searchResults = locationsRepository.search(query)
                _uiState.update {
                    it.copy(
                        searchResults = searchResults,
                        isLoadingSearchResults = false
                    )
                }
            } catch (e: Throwable) {
                _uiState.update { it.copy(isError = true) }
                Log.e("API", "searchLocation: $e")
            }
        }
    }
}