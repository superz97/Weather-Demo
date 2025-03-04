package com.superz.weatherdemo.data.mapper_impl

import com.superz.weatherdemo.data.mapper.ApiMapper
import com.superz.weatherdemo.data.remote.model.ApiHourlyWeather
import com.superz.weatherdemo.domain.model.Hourly
import com.superz.weatherdemo.util.Util
import com.superz.weatherdemo.util.WeatherInfoItem

class ApiHourlyMapper: ApiMapper<Hourly, ApiHourlyWeather> {
    
    override fun mapToDomain(apiEntity: ApiHourlyWeather): Hourly {
        return Hourly(
            temperature = apiEntity.temperature2m,
            time = parseTime(apiEntity.time),
            weatherStatus = parseWeatherStatus(apiEntity.weatherCode)
        )
    }
    
    private fun parseTime(time: List<Long>): List<String> {
        return time.map { Util.formatUnixDate("HH:mm", it) }
    }
    
    private fun parseWeatherStatus(code: List<Int>): List<WeatherInfoItem> {
        return code.map { 
            Util.getWeatherInfo(it)
        }
    }

}