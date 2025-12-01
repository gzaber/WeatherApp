package com.gzaber.weatherapp.ui.weather.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.gzaber.weatherapp.R

@Composable
fun WeatherForecastCard(
    imageLink: String,
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
            AsyncImage(
                model = imageLink,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_image),
                contentDescription = "Weather condition image",
                modifier = Modifier.size(35.dp)
            )
            Text(text = bottomText)
        }
    }
}