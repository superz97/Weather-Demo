package com.superz.weatherdemo.data.repository

import com.superz.weatherdemo.data.mapper_impl.ApiWeatherMapper
import com.superz.weatherdemo.data.remote.WeatherApi
import com.superz.weatherdemo.domain.model.Weather
import com.superz.weatherdemo.domain.repository.WeatherRepository
import com.superz.weatherdemo.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImplementation @Inject constructor(
    private val weatherApi: WeatherApi,
    private val apiWeatherMapper: ApiWeatherMapper
): WeatherRepository {
    override fun getWeatherData(): Flow<Response<Weather>> = flow {
        emit(Response.Loading())
        val apiWeather = weatherApi.getWeatherData()
        val weather = apiWeatherMapper.mapToDomain(apiWeather)
        emit(Response.Success(weather))
    }. catch { e->
        e.printStackTrace()
        emit(Response.Error(e.message.orEmpty()))
    }
}