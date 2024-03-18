package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.remote.WeatherDataDailyDto
import com.example.weatherapp.data.remote.WeatherDataHourlyDto
import com.example.weatherapp.data.remote.WeatherDto
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherDataDay
import com.example.weatherapp.domain.weather.WeatherDataDayDetailed
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import timber.log.Timber

private data class IndexedWeatherData(
    val index: Int, val data: WeatherData
)

private data class IndexedWeatherDataDay(
    val index: Int, val data: WeatherDataDay
)

private data class IndexedWeatherDataDayDetailed(
    val index: Int, val data: WeatherDataDayDetailed
)

fun WeatherDataHourlyDto.toWeatherDataPerDay(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        Timber.tag("toWeatherDataPerDay").d("time $time index ${index.toString()} weatherCode ${weatherCode.toString()}")
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

fun WeatherDataHourlyDto.toWeatherDataPerWeek(): Map<Int, List<WeatherDataDay>> {
    return time.mapIndexed { index, time ->
        Timber.tag("toWeatherDataMap").d("time $time index ${index.toString()} weatherCode ${weatherCode[index]}")
//        temperature[index]  ${temperature[index]}  weatherCode[index]  ${weatherCode[index]}
        val temperature = temperature[index]
        val weatherCode = weatherCode[index]
        val windSpeed = windSpeed[index]
        val pressure = pressure[index]
        val humidity = humidity[index]
        IndexedWeatherDataDay(
            index = index, data = WeatherDataDay(
                day = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                humidity = humidity,
                windSpeed = windSpeed,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }
        .groupBy {  // return abrunebs lists, times mixedvit, group by agroupebs per day of month rogorc mapshia
            it.index / 7
        }.mapValues {
            it.value.map { it.data }
        }
}

fun WeatherDataDailyDto.toWeatherDataPerWeekDetailed(): Map<Int, List<WeatherDataDayDetailed>> {
    return time.mapIndexed { index, time ->
        Timber.tag("toWeatherDataMap").d("time $time index ${index.toString()} weatherCode ${weatherCode[index]}")
        val precipitationSum = precipitationSum[index]
        val temperatureMax = temperatureMax[index]
        val temperatureMin = temperatureMin[index]
        val sunrise = sunrise[index]
        val sunset = sunset[index]
        val windDirectionDominant = windDirectionDominant[index]
        val windSpeedMax = windSpeedMax[index]
        val weatherCode = weatherCode[index]
        IndexedWeatherDataDayDetailed(
            index = index, data = WeatherDataDayDetailed(
                precipitationSum = precipitationSum,
                temperatureMax = temperatureMax,
                temperatureMin = temperatureMin,
                sunrise = sunrise,
                sunset = sunset,
                windDirectionDominant = windDirectionDominant,
                windSpeedMax = windSpeedMax,
                time = time,
                weatherCode = weatherCode)
        )
    }
        .groupBy {  // return abrunebs lists, times mixedvit, group by agroupebs per day of month rogorc mapshia
            it.index / 7
        }.mapValues {
            it.value.map { it.data }
        }
}



fun WeatherDto.toWeatherInfo(): WeatherInfo {
    Timber.tag("toWeatherInfo").d("weatherData  $weatherDataHourly  ")
    //perDay
    val weatherDataMap = weatherDataHourly.toWeatherDataPerDay()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    val weatherPerWeekData = weatherDataHourly.toWeatherDataPerWeek()
    Timber.tag("toWeatherInfo").d("\n \n weatherDataMap $weatherDataMap \n \n weatherPerWeekData  $weatherPerWeekData \n \n 7 - ${weatherPerWeekData?.get(7)} ")

    return WeatherInfo(
        weatherDataMap, currentWeatherData, weatherPerWeekData
    )
}