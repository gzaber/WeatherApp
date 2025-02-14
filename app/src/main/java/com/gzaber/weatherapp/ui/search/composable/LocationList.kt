package com.gzaber.weatherapp.ui.search.composable

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gzaber.weatherapp.data.repository.locations.model.Location

@Composable
fun LocationList(
    locations: List<Location>,
    onLocationClick: (Location) -> Unit,
    modifier: Modifier = Modifier,
    onLocationSwipe: ((Location) -> Unit)? = null
) {
    LazyColumn(modifier) {
        items(locations, key = { it.id }) { location ->
            if (onLocationSwipe != null) {
                SwipeableLocationListItem(
                    location = location,
                    onLocationClick = onLocationClick,
                    onLocationSwipe = onLocationSwipe
                )
            } else {
                LocationListItem(
                    location = location,
                    onLocationClick = onLocationClick
                )
            }
        }
    }
}