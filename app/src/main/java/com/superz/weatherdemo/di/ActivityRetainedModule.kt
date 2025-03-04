package com.superz.weatherdemo.di

import com.superz.weatherdemo.data.repository.WeatherRepositoryImplementation
import com.superz.weatherdemo.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    @ActivityRetainedScoped
    abstract fun bindWeatherRepository(weatherRepositoryImplementation: WeatherRepositoryImplementation): WeatherRepository
}