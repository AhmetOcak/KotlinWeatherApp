package com.kotlinweatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinweatherapp.api.RetrofitInstance
import com.kotlinweatherapp.data.WeatherModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class WeatherViewModel : ViewModel() {

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String> get() = _cityName

    private val _temp = MutableLiveData<String>()
    val temp: LiveData<String> get() = _temp

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> get() = _description

    private val _feelsLike = MutableLiveData<String>()
    val feelsLike: LiveData<String> get() = _feelsLike

    private val _windSpeed = MutableLiveData<String>()
    val windSpeed: LiveData<String> get() = _windSpeed

    private val _humidity = MutableLiveData<String>()
    val humidity: LiveData<String> get() = _humidity

    private val _sunrise = MutableLiveData<String>()
    val sunrise: LiveData<String> get() = _sunrise

    private val _sunset = MutableLiveData<String>()
    val sunset: LiveData<String> get() = _sunset

    private val _pressure = MutableLiveData<String>()
    val pressure: LiveData<String> get() = _pressure

    init {
        viewModelScope.launch {
            RetrofitInstance.getWeatherData("ankara").body()?.let { setWeatherData(it) }
        }
    }

    private fun readTimestamp(timestamp: Long): String? {
        val formatter = SimpleDateFormat("hh:mm aa")
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp * 1000L
        calendar.add(Calendar.HOUR, 3)
        return formatter.format(calendar.time)
    }

    private fun setWeatherData(weatherModel: WeatherModel) {
        _cityName.value = weatherModel.name
        _description.value = weatherModel.weather[0].description
        _temp.value = weatherModel.main.temp.toInt().toString() + "°"
        _feelsLike.value = weatherModel.main.feels_like.toInt().toString()
        _windSpeed.value = weatherModel.wind.speed.toInt().toString()
        _humidity.value = weatherModel.main.humidity.toString()
        _sunrise.value = readTimestamp(weatherModel.sys.sunrise)!!
        _sunset.value = readTimestamp(weatherModel.sys.sunset)!!
        _pressure.value = weatherModel.main.pressure.toInt().toString()
    }

}