package com.gzaber.weatherapp.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gzaber.weatherapp.R
import com.gzaber.weatherapp.ui.search.composable.LocationList
import com.gzaber.weatherapp.ui.search.composable.SearchBar
import com.gzaber.weatherapp.ui.util.composable.LoadingIndicator
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onNavigateBack: () -> Unit,
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
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = "Navigate back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                SearchBar(
                    searchText = uiState.searchText,
                    onSearchTextChanged = viewModel::onSearchTextChanged,
                    onSearchTextCleared = viewModel::onSearchTextCleared
                )

                if (uiState.searchResults.isNotEmpty()) {
                    if (uiState.isLoadingSearchResults) {
                        LoadingIndicator(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = contentPadding
                        )
                    } else {
                        LocationList(
                            locations = uiState.searchResults,
                            onLocationClick = { location ->
                                viewModel.selectLocation(location)
                                onNavigateBack()
                            }
                        )
                    }
                } else {
                    if (uiState.isLoadingLocationHistory) {
                        LoadingIndicator(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = contentPadding
                        )
                    } else {
                        Text(
                            text = "Recent searches",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        if (uiState.locationHistory.isEmpty()) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = "There is no history yet",
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        } else {
                            LocationList(
                                locations = uiState.locationHistory,
                                onLocationClick = { location ->
                                    viewModel.selectLocation(location)
                                    onNavigateBack()
                                },
                                onLocationSwipe = viewModel::removeFromLocationHistory
                            )
                        }
                    }
                }
            }
        }
    }
}
