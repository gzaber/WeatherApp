package com.gzaber.weatherapp.ui.search

import com.gzaber.weatherapp.data.repository.locations.model.Location

data class SearchUiState(
    val searchText: String = "",
    val searchResults: List<Location> = emptyList(),
    val locationHistory: List<Location> = emptyList(),
    val isLoadingSearchResults: Boolean = false,
    val isLoadingLocationHistory: Boolean = false,
    val isError: Boolean = false
)
