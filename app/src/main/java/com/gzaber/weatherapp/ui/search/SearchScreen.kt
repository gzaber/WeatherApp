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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    viewModel: SearchViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchState = uiState.searchState
    val errorMessage = stringResource(R.string.generic_error_message)

    LaunchedEffect(searchState) {
        if (searchState is SearchState.Error) {
            snackbarHostState.showSnackbar(errorMessage)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)  },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.search_screen_title),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = stringResource(R.string.navigate_back_content_description)
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
                    searchText = uiState.query,
                    onSearchTextChanged = viewModel::onSearchTextChanged,
                    onSearchTextCleared = viewModel::onSearchTextCleared
                )

                when (searchState) {
                    is SearchState.Loading -> {
                        LoadingIndicator(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = contentPadding
                        )
                    }

                    is SearchState.Success -> {
                        LocationList(
                            locations = searchState.locations,
                            onLocationClick = { location ->
                                viewModel.selectLocation(location)
                                onNavigateBack()
                            }
                        )
                    }

                    is SearchState.Error -> {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = stringResource(R.string.generic_error_message),
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }

                    is SearchState.Empty -> {
                        Text(
                            text = stringResource(R.string.recent_searches_title),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        if (uiState.savedLocations.isEmpty()) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = stringResource(R.string.no_history_message),
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        } else {
                            LocationList(
                                locations = uiState.savedLocations,
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
