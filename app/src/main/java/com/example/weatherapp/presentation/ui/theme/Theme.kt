package com.example.weatherapp.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material.Typography


@Composable
fun WeatherAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        shapes = Shapes,
        content = content
    )
//    typography = androidx.compose.material.Typography,

}
