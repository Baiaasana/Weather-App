package com.example.weatherapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text

@Composable
fun WeatherDailyForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
){
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "Daily",
                color = Color.White,
                fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            // recycler
            LazyRow(content = {
                items(data) { weatherData ->
                    //single item
                    HourlyWeatherView(weatherData = weatherData,
                        modifier = Modifier.height(80.dp).padding(horizontal = 16.dp))
                }
            })
        }
    }


}