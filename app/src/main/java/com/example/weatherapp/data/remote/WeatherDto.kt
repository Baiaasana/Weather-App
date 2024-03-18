package com.example.weatherapp.data.remote

import com.squareup.moshi.Json

data class WeatherDto(
    @field:Json(name = "hourly")
    val weatherDataHourly: WeatherDataHourlyDto,
    @field:Json(name = "daily")
    val weathersDataDaily: WeatherDataDailyDto
){

}
