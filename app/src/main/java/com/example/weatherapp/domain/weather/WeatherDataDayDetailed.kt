package com.example.weatherapp.domain.weather

class WeatherDataDayDetailed(
    val precipitationSum: Double,
    val temperatureMax: Double,
    val temperatureMin: Double,
    val sunrise: String,
    val sunset: String,
    val windDirectionDominant: Int,
    val windSpeedMax: Double,
    val time: String,
    val weatherCode: Int
)