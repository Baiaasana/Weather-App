package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.remote.WeatherDataDto
import com.example.weatherapp.data.remote.WeatherDto
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import timber.log.Timber

private data class IndexedWeatherData(
    val index: Int, val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        Timber.tag("toWeatherDataMap").d("time $time index ${index.toString()} weatherCode ${weatherCode.toString()}")
//        temperature[index]  ${temperature[index]}  weatherCode[index]  ${weatherCode[index]}
        val temperature = temperature[index]
        val weatherCode = weatherCode[index]
        val windSpeed = windSpeed[index]
        val pressure = pressure[index]
        val humidity = humidity[index]
        IndexedWeatherData(
            index = index, data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                humidity = humidity,
                windSpeed = windSpeed,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )

    }
        .groupBy {  // return abrunebs lists, times mixedvit, group by agroupebs per day of month rogorc mapshia
            it.index / 24
        }.mapValues {
            it.value.map { it.data }
        }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataMap, currentWeatherData
    )
}