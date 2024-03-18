package com.example.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

//    https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m,relative_humidity_2m,,pressure_msl,wind_speed_10m,weather_code

    @GET("v1/forecast?hourly=temperature_2m,relative_humidity_2m,pressure_msl,wind_speed_10m,weather_code")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double, @Query("longitude") lon: Double
    ): WeatherDto
}