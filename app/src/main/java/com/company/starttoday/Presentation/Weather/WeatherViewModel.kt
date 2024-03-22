package com.company.starttoday.Presentation.Weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.starttoday.Core.Network.API.WeatherAPI
import com.company.starttoday.Data.Impl.forecastRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: forecastRepositoryImpl,
    private val weatherApi: WeatherAPI
) : ViewModel() {

    val rainPossible : StateFlow<List<Int>> = repository.rainPossible

    suspend fun getWeather(nx : Int , ny : Int) =
        viewModelScope.launch {
            repository.getVillageForecast(nx , ny , "0000")
        }

    init {

    }





}