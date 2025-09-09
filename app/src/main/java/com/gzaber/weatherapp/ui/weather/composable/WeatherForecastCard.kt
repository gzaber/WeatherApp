package com.gzaber.weatherapp.ui.weather.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun WeatherForecastCard(
    @DrawableRes icon: Int,
    topText: String,
    bottomText: String,
    modifier: Modifier = Modifier
) {
    OutlinedCard(modifier = modifier.padding(horizontal = 4.dp)) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = topText)
            Icon(
                painter = painterResource(icon),
                contentDescription = "",
            )
            Text(text = bottomText)
        }
    }
}