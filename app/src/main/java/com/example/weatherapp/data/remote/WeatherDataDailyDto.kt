package com.example.weatherapp.data.remote

import com.squareup.moshi.Json

data class WeatherDataDailyDto(
    @field:Json(name = "precipitation_sum")
    val precipitationSum: List<Double>,
    @field:Json(name = "temperature_2m_max")
    val temperatureMax: List<Double>,
    @field:Json(name = "temperature_2m_min")
    val temperatureMin: List<Double>,
    @field:Json(name = "sunrise")
    val sunrise: List<String>,
    @field:Json(name = "sunset")
    val sunset: List<String>,
    @field:Json(name = "wind_direction_10m_dominant")
    val windDirectionDominant: List<Int>,
    @field:Json(name = "wind_speed_10m_max")
    val windSpeedMax: List<Double>,
    val time:List<String>,
    @field:Json(name = "weather_code")
    val weatherCode:List<Int>
)