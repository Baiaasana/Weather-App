package com.example.weatherapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.location.LocationTracker
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository, private val locationTracker: LocationTracker
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            locationTracker.getCurrentLocation()?.let { location ->
                when (val result = repository.getData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        state =
                            state.copy(weatherInfo = result.data, isLoading = false, error = null)
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            error = result.message.toString(), isLoading = false, weatherInfo = null
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(isLoading = false, error = "Couldn't retrieve location.")
            }
        }
    }
}