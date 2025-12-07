package com.gzaber.weatherapp.ui.search

import com.gzaber.weatherapp.data.repository.locations.model.Location

sealed interface SearchState {
    data class Success(val locations: List<Location>) : SearchState
    object Error : SearchState
    object Loading : SearchState
    object Empty : SearchState
}

data class SearchUiState(
    val searchState: SearchState = SearchState.Empty,
    val savedLocations: List<Location> = emptyList(),
    val query: String = ""
)
