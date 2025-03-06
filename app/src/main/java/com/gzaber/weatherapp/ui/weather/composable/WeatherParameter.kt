package com.gzaber.weatherapp.ui.weather.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun WeatherParameter(
    icon: ImageVector,
    iconContentDescription: String,
    value: String,
    unit: String,
    description: String,
    modifier: Modifier = Modifier,
    parameterTextStyle: TextStyle = MaterialTheme.typography.titleLarge,
    descriptionTextStyle: TextStyle = MaterialTheme.typography.titleMedium,
    iconSize: Dp = 30.dp
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconContentDescription,
            modifier = Modifier.size(iconSize)
        )
        Text(text = "$value$unit", style = parameterTextStyle)
        Text(text = description, style = descriptionTextStyle)
    }
}