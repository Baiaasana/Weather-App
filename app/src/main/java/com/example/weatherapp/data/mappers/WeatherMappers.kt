package com.example.weatherapp.data.mappers

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weatherapp.data.remote.WeatherDataDto
import com.example.weatherapp.data.remote.WeatherDto
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
private data class  IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)
@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDto.toWeatherDataMap():  Map<Int, List<WeatherData>> {
    return time.mapIndexed{index, time ->
        val temperature = temperature[index]
        val weatherCode = weatherCode[index]
        val windSpeed = windSpeed[index]
        val pressure = pressure[index]
        val humidity = humidity[index]
        IndexedWeatherData(
            index = index,
            data =  WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                humidity = humidity,
                windSpeed = windSpeed,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )

    }.groupBy {  // return abrunebs lists, times mixedvit, group by agroupebs per day of month rogorc mapshia
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDto.toWeatherInfo():WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if(now.minute < 30) now.hour else now.hour +1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataMap, currentWeatherData
    )
}