package com.superz.weatherdemo.data.mapper_impl

import com.superz.weatherdemo.data.mapper.ApiMapper
import com.superz.weatherdemo.data.remote.model.ApiCurrentWeather
import com.superz.weatherdemo.domain.model.CurrentWeather
import com.superz.weatherdemo.util.Util
import com.superz.weatherdemo.util.WeatherInfoItem

class CurrentWeatherMapper: ApiMapper<CurrentWeather, ApiCurrentWeather> {

    override fun mapToDomain(apiEntity: ApiCurrentWeather): CurrentWeather {
        return CurrentWeather(
            temperature = apiEntity.temperature2m,
            time = parseTime(apiEntity.time),
            weatherStatus = parseWeatherStatus(apiEntity.weatherCode),
            windDirection = parseWindDirection(apiEntity.windDirection10m),
            windSpeed = apiEntity.windSpeed10m,
            isDay = apiEntity.isDay == 1
        )
    }

    private fun parseTime(time: Long): String {
        return Util.formatUnixDate("MMM,d", time)
    }

    private fun parseWeatherStatus(code: Int): WeatherInfoItem {
        return Util.getWeatherInfo(code)
    }

    private fun parseWindDirection(windDirection: Double): String {
        return Util.getWindDirection(windDirection)
    }

}