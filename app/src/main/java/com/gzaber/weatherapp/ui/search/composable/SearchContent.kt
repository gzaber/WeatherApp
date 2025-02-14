package com.gzaber.weatherapp.ui.search.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gzaber.weatherapp.data.repository.locations.model.Location

@Composable
fun SearchContent(
    searchText: String,
    searchResults: List<Location>,
    locationHistory: List<Location>,
    contentPadding: PaddingValues,
    onSearchTextChanged: (String) -> Unit,
    onSearchTextCleared: () -> Unit,
    onLocationClick: (Location) -> Unit,
    onLocationSwipe: (Location) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SearchBar(
                searchText = searchText,
                onSearchTextChanged = onSearchTextChanged,
                onSearchTextCleared = onSearchTextCleared
            )

            if (searchResults.isNotEmpty()) {
                LocationList(
                    locations = searchResults,
                    onLocationClick = onLocationClick
                )
            } else {
                Text(
                    text = "Recent searches",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
                if (locationHistory.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = "There is no history yet",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                } else {
                    LocationList(
                        locations = locationHistory,
                        onLocationClick = onLocationClick,
                        onLocationSwipe = onLocationSwipe
                    )
                }
            }
        }
    }
}

