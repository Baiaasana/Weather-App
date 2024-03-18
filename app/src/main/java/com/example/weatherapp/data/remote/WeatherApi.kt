package com.example.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

//    https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m,relative_humidity_2m,,pressure_msl,wind_speed_10m,weather_code

    @GET("v1/forecast?hourly=temperature_2m,relative_humidity_2m,pressure_msl,wind_speed_10m,weather_code")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double, @Query("longitude") lon: Double
    ): WeatherDto


    // daily
//    https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&daily=weather_code,precipitation_sum,temperature_2m_max,temperature_2m_min,sunrise,sunset,wind_direction_10m_dominant,wind_speed_10m_max
    @GET("v1/forecast?hourly=temperature_2m,relative_humidity_2m,pressure_msl,wind_speed_10m,weather_code&daily=weather_code,precipitation_sum,temperature_2m_max,temperature_2m_min,sunrise,sunset,wind_direction_10m_dominant,wind_speed_10m_max")
    suspend fun getWeatherData2(
        @Query("latitude") lat: Double, @Query("longitude") lon: Double
    ): WeatherDto


//full
//    https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m,weather_code,wind_speed_10m&daily=weather_code,precipitation_sum,temperature_2m_max,temperature_2m_min,sunrise,sunset,wind_direction_10m_dominant,wind_speed_10m_max

}