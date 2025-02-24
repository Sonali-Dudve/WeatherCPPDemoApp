package com.spdfs.weathercppdemoapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spdfs.weathercppdemoapp.RetrofitInstance
import com.spdfs.weathercppdemoapp.constants.KeyConstants
import com.spdfs.weathercppdemoapp.model.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherStateFlow: StateFlow<WeatherState> = weatherState

    private val apiKey = KeyConstants.getWeatherAPIKey()


    fun getWeatherData(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getWeather(
                    lat = lat,
                    lon = lon,
                    apiKey = apiKey
                )
                weatherState.value = WeatherState.Success(response)
            } catch (e: Exception) {
                weatherState.value = WeatherState.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }
}

sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val weatherResponse: WeatherResponse) : WeatherState()
    data class Error(val message: String) : WeatherState()
}
