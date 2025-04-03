package com.gzaber.weatherapp.ui.search.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gzaber.weatherapp.data.repository.locations.model.Location

@Composable
fun LocationListItem(
    location: Location,
    onLocationClick: (Location) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ListItem(
            headlineContent = {
                Text(location.name)
            },
            supportingContent = {
                Text(location.country)
            },
            leadingContent = {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location"
                )
            },
            modifier = Modifier.clickable { onLocationClick(location) }
        )
        HorizontalDivider()
    }
}