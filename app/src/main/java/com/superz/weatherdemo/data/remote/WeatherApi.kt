package com.superz.weatherdemo.data.remote

import com.superz.weatherdemo.data.remote.model.ApiWeather
import com.superz.weatherdemo.util.ApiParameters
import com.superz.weatherdemo.util.K
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET(K.END_POINT)
    suspend fun getWeatherData(
        @Query(ApiParameters.LATITUDE) latitude: Float = 43.2567f,
        @Query(ApiParameters.LONGITUDE) longitude: Float = 76.9286f,
        @Query(ApiParameters.DAILY) daily: Array<String> = arrayOf(
            "sunrise",
            "sunset",
            "temperature_2m_max",
            "temperature_2m_min",
            "uv_index_max",
            "weather_code",
            "wind_direction_10m_dominant",
            "wind_speed_10m_max"
        ),
        @Query(ApiParameters.CURRENT_WEATHER) currentWeather: Array<String> = arrayOf(
            "temperature_2m",
            "weather_code",
            "wind_direction_10m",
            "wind_speed_10m",
            "is_day"
        ),
        @Query(ApiParameters.HOURLY) hourlyWeather: Array<String> = arrayOf(
            "weather_code",
            "temperature_2m"
        ),
        @Query(ApiParameters.TIME_FORMAT) timeformat: String = "unixtime",
        @Query(ApiParameters.TIMEZONE) timeZone: String = "Asia/Almaty"
    ): ApiWeather

}