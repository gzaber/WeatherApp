package com.gzaber.weatherapp.ui.search

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import com.gzaber.weatherapp.ui.search.composable.SearchContent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Search",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { contentPadding ->
        SearchContent(
            searchText = uiState.searchText,
            searchResults = uiState.searchResults,
            locationHistory = uiState.locationHistory,
            contentPadding = contentPadding,
            onSearchTextChanged = viewModel::onSearchTextChanged,
            onSearchTextCleared = viewModel::onSearchTextCleared,
            onLocationClick = viewModel::saveToLocationHistory,
            onLocationSwipe = viewModel::removeFromLocationHistory
        )
    }
}
