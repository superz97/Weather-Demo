package com.superz.weatherdemo.domain.repository

import com.superz.weatherdemo.domain.model.Weather
import com.superz.weatherdemo.util.Response
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherData(): Flow<Response<Weather>>
}