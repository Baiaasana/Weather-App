package com.example.weatherapp.domain.weather

import java.time.LocalDateTime

class WeatherDataDay(
    val day: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType,
)