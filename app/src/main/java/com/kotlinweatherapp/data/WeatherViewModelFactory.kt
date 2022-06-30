package com.kotlinweatherapp.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlinweatherapp.db.WeatherDatabase
import com.kotlinweatherapp.utilities.Constants
import com.kotlinweatherapp.viewmodels.WeatherViewModel
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
            throw IllegalArgumentException(Constants.EXCEPTION_MESSAGE)
        }
    }
}