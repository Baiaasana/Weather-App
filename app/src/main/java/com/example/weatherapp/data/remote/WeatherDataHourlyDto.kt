package com.example.weatherapp.data.remote

import com.squareup.moshi.Json

data class WeatherDataHourlyDto(
    @field:Json(name = "relative_humidity_2m")
    val humidity: List<Double>,
    @field:Json(name = "temperature_2m")
    val temperature: List<Double>,
    @field:Json(name = "pressure_msl")
    val pressure: List<Double>,
    @field:Json(name = "wind_speed_10m")
    val windSpeed: List<Double>,
    val time:List<String>,
    @field:Json(name = "weather_code")
    val weatherCode:List<Int>
)