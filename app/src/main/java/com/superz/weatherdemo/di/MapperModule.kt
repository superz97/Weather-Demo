package com.superz.weatherdemo.di

import com.superz.weatherdemo.data.mapper.ApiMapper
import com.superz.weatherdemo.data.mapper_impl.ApiDailyMapper
import com.superz.weatherdemo.data.mapper_impl.ApiHourlyMapper
import com.superz.weatherdemo.data.mapper_impl.ApiWeatherMapper
import com.superz.weatherdemo.data.mapper_impl.CurrentWeatherMapper
import com.superz.weatherdemo.data.remote.model.ApiCurrentWeather
import com.superz.weatherdemo.data.remote.model.ApiDailyWeather
import com.superz.weatherdemo.data.remote.model.ApiHourlyWeather
import com.superz.weatherdemo.data.remote.model.ApiWeather
import com.superz.weatherdemo.domain.model.CurrentWeather
import com.superz.weatherdemo.domain.model.Daily
import com.superz.weatherdemo.domain.model.Hourly
import com.superz.weatherdemo.domain.model.Weather
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @ApiDailyWeatherMapperAnnotation
    @Provides
    fun provideDailyApiMapper(): ApiMapper<Daily, ApiDailyWeather> {
        return ApiDailyMapper()
    }

    @ApiHourlyWeatherMapperAnnotation
    @Provides
    fun provideHourlyApiMapper(): ApiMapper<Hourly, ApiHourlyWeather> {
        return ApiHourlyMapper()
    }

    @ApiCurrentWeatherMapperAnnotation
    @Provides
    fun provideCurrentApiMapper(): ApiMapper<CurrentWeather, ApiCurrentWeather> {
        return CurrentWeatherMapper()
    }

    @ApiWeatherMapperAnnotation
    @Provides
    fun provideWeatherApiMapper(
        apiDailyMapper: ApiMapper<Daily, ApiDailyWeather>,
        apiCurrentWeatherMapper: ApiMapper<CurrentWeather, ApiCurrentWeather>,
        apiHourlyMapper: ApiMapper<Hourly, ApiHourlyWeather>
    ): ApiMapper<Weather, ApiWeather> {
        return ApiWeatherMapper(
            apiDailyMapper = apiDailyMapper,
            apiCurrentWeatherMapper = apiCurrentWeatherMapper,
            apiHourlyMapper = apiHourlyMapper
        )
    }

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiDailyWeatherMapperAnnotation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiHourlyWeatherMapperAnnotation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiCurrentWeatherMapperAnnotation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiWeatherMapperAnnotation