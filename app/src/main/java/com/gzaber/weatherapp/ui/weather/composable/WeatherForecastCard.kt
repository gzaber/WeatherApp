package com.gzaber.weatherapp.ui.weather.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gzaber.weatherapp.R

@Composable
fun WeatherForecastCard(
    @DrawableRes iconRes: Int,
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
            Image(
                painterResource(iconRes),
                contentDescription = stringResource(R.string.weather_condition_image_content_description),
                modifier = Modifier.size(48.dp)
            )
            Text(text = bottomText)
        }
    }
}