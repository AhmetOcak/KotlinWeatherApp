package com.kotlinweatherapp.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinweatherapp.api.RetrofitInstance
import com.kotlinweatherapp.data.WeatherModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class WeatherViewModel(city: String) : ViewModel() {

    private val _cityName = MutableLiveData(city)
    val cityName: LiveData<String> get() = _cityName

    private val _temp = MutableLiveData("")
    val temp: LiveData<String> get() = _temp

    private val _description = MutableLiveData("")
    val description: LiveData<String> get() = _description

    private val _feelsLike = MutableLiveData("")
    val feelsLike: LiveData<String> get() = _feelsLike

    private val _windSpeed = MutableLiveData("")
    val windSpeed: LiveData<String> get() = _windSpeed

    private val _humidity = MutableLiveData("")
    val humidity: LiveData<String> get() = _humidity

    private val _sunrise = MutableLiveData("")
    val sunrise: LiveData<String> get() = _sunrise

    private val _sunset = MutableLiveData("")
    val sunset: LiveData<String> get() = _sunset

    private val _pressure = MutableLiveData("")
    val pressure: LiveData<String> get() = _pressure

    private val _viewVisibility = MutableLiveData(View.GONE)
    val viewVisibility: LiveData<Int?> get() = _viewVisibility

    private val _errorMessageVisibility = MutableLiveData(View.GONE)
    val errorMessageVisibility: LiveData<Int?> get() = _errorMessageVisibility

    private val _data = MutableLiveData<Response<WeatherModel>>()

    private val _errorText = MutableLiveData<String>()
    val errorText: LiveData<String> get() = _errorText

    init {
        getWeatherData()
    }

    private fun getWeatherData() {
        viewModelScope.launch {
            _data.value = RetrofitInstance.getWeatherData(_cityName.value.toString())
            Log.e("data", _data.value.toString())
            checkDataAvailable()
        }
    }

    private fun readTimestamp(timestamp: Long): String? {
        val formatter = SimpleDateFormat("hh:mm")
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp * 1000L
        return formatter.format(calendar.time)
    }

    private fun setWeatherData(weatherModel: WeatherModel) {
        _cityName.value = weatherModel.name
        _description.value = weatherModel.weather[0].description
        _temp.value = weatherModel.main.temp.toInt().toString()
        _feelsLike.value = weatherModel.main.feels_like.toInt().toString()
        _windSpeed.value = weatherModel.wind.speed.toInt().toString()
        _humidity.value = weatherModel.main.humidity.toString()
        _sunrise.value = readTimestamp(weatherModel.sys.sunrise)!!
        _sunset.value = readTimestamp(weatherModel.sys.sunset)!!
        _pressure.value = weatherModel.main.pressure.toInt().toString()
    }

    private fun checkDataAvailable() {
        when {
            _data.value?.code() == 200 -> {
                _viewVisibility.value = View.VISIBLE
                _errorMessageVisibility.value = View.GONE
                setWeatherData(_data.value!!.body()!!)
            }
            _data.value?.code() == 404 -> {
                _viewVisibility.value = View.GONE
                _errorText.value = "City not found"
                _errorMessageVisibility.value = View.VISIBLE
            }
            _data.value?.code() == 401 -> {
                _viewVisibility.value = View.GONE
                _errorText.value = "Unauthorized"
                _errorMessageVisibility.value = View.VISIBLE
            }
        }
    }
}