package com.superz.weatherdemo.data.mapper_impl

import com.superz.weatherdemo.data.mapper.ApiMapper
import com.superz.weatherdemo.data.remote.model.ApiDailyWeather
import com.superz.weatherdemo.domain.model.Daily
import com.superz.weatherdemo.util.Util
import com.superz.weatherdemo.util.WeatherInfoItem

class ApiDailyMapper: ApiMapper<Daily, ApiDailyWeather> {

    override fun mapToDomain(apiEntity: ApiDailyWeather): Daily {
        return Daily(
            temperatureMax = apiEntity.temperature2mMax,
            temperatureMin = apiEntity.temperature2mMin,
            time = parseTime(apiEntity.time),
            weatherStatus = parseWeatherStatus(apiEntity.weatherCode),
            windDirection = parseWeatherDirection(apiEntity.windDirection10mDominant),
            windSpeed = apiEntity.windSpeed10mMax,
            sunrise = apiEntity.sunrise.map { Util.formatUnixDate("HH:mm", it.toLong()) },
            sunset = apiEntity.sunset.map { Util.formatUnixDate("HH:mm", it.toLong()) },
            uvIndex = apiEntity.uvIndexMax,
        )
    }

    private fun parseTime(time: List<Long>): List<String> {
        return time.map { Util.formatUnixDate("E", it) }
    }

    private fun parseWeatherStatus(code: List<Int>): List<WeatherInfoItem> {
        return code.map { Util.getWeatherInfo(it) }
    }

    private fun parseWeatherDirection(windDirections: List<Double>): List<String> {
        return windDirections.map { Util.getWindDirection(it) }
    }

}