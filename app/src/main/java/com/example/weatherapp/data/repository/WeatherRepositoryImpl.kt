package com.example.weatherapp.data.repository

import com.example.weatherapp.data.mappers.toWeatherInfo
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.util.Resource
import com.example.weatherapp.domain.weather.WeatherInfo
import timber.log.Timber
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api:WeatherApi
):WeatherRepository {
    override suspend fun getData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Timber.tag("toWeatherDataMap").d(" result success ${api.getWeatherData(
                lat, long
            )} ")
            Resource.Success(
                data = api.getWeatherData(
                    lat, long
                ).toWeatherInfo()
            )

        } catch (e:Exception){
            e.printStackTrace()
            Resource.Error(e.message ?: "Unknown error")
        }
    }


}