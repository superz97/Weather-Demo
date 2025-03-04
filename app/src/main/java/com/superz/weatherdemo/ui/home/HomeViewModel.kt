package com.superz.weatherdemo.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.superz.weatherdemo.domain.model.Daily
import com.superz.weatherdemo.domain.model.Weather
import com.superz.weatherdemo.domain.repository.WeatherRepository
import com.superz.weatherdemo.util.Response
import com.superz.weatherdemo.util.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    var homeState by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch {
            repository.getWeatherData().collect {response ->
                when (response) {
                    is Response.Loading -> {
                        homeState = homeState.copy(
                            isLoading = true
                        )
                    }
                    is Response.Success -> {
                        homeState = homeState.copy(
                            isLoading = false,
                            error = null,
                            weather = response.data
                        )
                        val todayWeatherInfo = response.data?.daily?.weatherInfo?.find {
                            Util.isTodayDate(it.time)
                        }
                        homeState = homeState.copy(
                            dailyWeatherInfo = todayWeatherInfo
                        )
                    }
                    is Response.Error -> {
                        homeState = homeState.copy(
                            isLoading = false,
                            error = response.message
                        )
                    }
                }
            }
        }
    }

}

data class HomeState(
    val weather: Weather? = null,
    val error: String? = null,
    val isLoading:Boolean = false,
    val dailyWeatherInfo: Daily.WeatherInfo? = null
)