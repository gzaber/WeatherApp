package com.gzaber.weatherapp.ui.search.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.gzaber.weatherapp.data.repository.locations.model.Location

@Composable
fun SwipeableLocationListItem(
    location: Location,
    onLocationClick: (Location) -> Unit,
    onLocationSwipe: (Location) -> Unit,
    modifier: Modifier = Modifier
) {
    SwipeToDismissBox(
        state = rememberSwipeToDismissBoxState(
            confirmValueChange = {
                onLocationSwipe(location)
                true
            }
        ),
        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            )
        },
        modifier = modifier
    )
    {
        LocationListItem(
            location = location,
            onLocationClick = onLocationClick
        )
    }
}