package com.superz.weatherdemo.data.mapper_impl

import com.superz.weatherdemo.data.mapper.ApiMapper
import com.superz.weatherdemo.data.remote.model.ApiCurrentWeather
import com.superz.weatherdemo.data.remote.model.ApiDailyWeather
import com.superz.weatherdemo.data.remote.model.ApiHourlyWeather
import com.superz.weatherdemo.data.remote.model.ApiWeather
import com.superz.weatherdemo.di.ApiCurrentWeatherMapperAnnotation
import com.superz.weatherdemo.di.ApiDailyWeatherMapperAnnotation
import com.superz.weatherdemo.di.ApiHourlyWeatherMapperAnnotation
import com.superz.weatherdemo.domain.model.CurrentWeather
import com.superz.weatherdemo.domain.model.Daily
import com.superz.weatherdemo.domain.model.Hourly
import com.superz.weatherdemo.domain.model.Weather
import javax.inject.Inject

class ApiWeatherMapper @Inject constructor(
    @ApiDailyWeatherMapperAnnotation private val apiDailyMapper: ApiMapper<Daily, ApiDailyWeather>,
    @ApiCurrentWeatherMapperAnnotation private val apiCurrentWeatherMapper: ApiMapper<CurrentWeather, ApiCurrentWeather>,
    @ApiHourlyWeatherMapperAnnotation private val apiHourlyMapper: ApiMapper<Hourly, ApiHourlyWeather>
): ApiMapper<Weather, ApiWeather> {
    
    override fun mapToDomain(apiEntity: ApiWeather): Weather {
        return Weather(
            currentWeather = apiCurrentWeatherMapper.mapToDomain(apiEntity.current),
            daily = apiDailyMapper.mapToDomain(apiEntity.daily),
            hourly = apiHourlyMapper.mapToDomain(apiEntity.hourly),
        )
    }

}