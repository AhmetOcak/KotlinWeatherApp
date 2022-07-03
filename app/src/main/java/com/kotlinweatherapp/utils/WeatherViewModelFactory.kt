package com.kotlinweatherapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlinweatherapp.data.LocationData
import com.kotlinweatherapp.data.local.db.WeatherDatabase
import com.kotlinweatherapp.ui.weather.WeatherViewModel
import java.lang.IllegalArgumentException

class WeatherViewModelFactory(
    private val city: String,
    private val locationData: LocationData,
    private val weatherDataDb: WeatherDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(city, locationData, weatherDataDb) as T
        } else {
            throw IllegalArgumentException(Constants.ErrorMessages.EXCEPTION_MESSAGE)
        }
    }
}