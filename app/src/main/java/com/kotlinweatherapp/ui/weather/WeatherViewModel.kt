package com.kotlinweatherapp.ui.weather

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinweatherapp.data.LocationData
import com.kotlinweatherapp.db.entity.WeatherDataModel
import com.kotlinweatherapp.data.model.WeatherModel
import com.kotlinweatherapp.data.repo.WeatherRepository
import com.kotlinweatherapp.db.WeatherDatabase
import com.kotlinweatherapp.utils.Constants
import com.kotlinweatherapp.utils.Status
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.*

class WeatherViewModel(
    city: String,
    private val locationData: LocationData,
    private val weatherDataDb: WeatherDatabase
) : ViewModel() {

    private val weatherRepository = WeatherRepository

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

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status> get() = _status

    private val _data = MutableLiveData<Response<WeatherModel>>()

    private var _dataComingFromLoc: Boolean = false

    private val _errorText = MutableLiveData<String>()
    val errorText: LiveData<String> get() = _errorText

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            _status.value = Status.LOADING
            try {
                if (_cityName.value.toString() == Constants.Strings.CITY_NAME_NULL) {
                    _dataComingFromLoc = true
                    _data.value = weatherRepository.getWeatherDataWithLocation(locationData)
                    checkDataAvailable()
                } else {
                    _dataComingFromLoc = false
                    _data.value =
                        weatherRepository.getWeatherDataWithCityName(_cityName.value.toString())
                    checkDataAvailable()
                }
            } catch (e: UnknownHostException) {
                if (_dataComingFromLoc && weatherDataDb.weatherDao().getWeatherData() != null) {
                    setWeatherDataFromDb()
                    _status.value = Status.DONE
                    _viewVisibility.value = View.VISIBLE
                    _errorMessageVisibility.value = View.GONE
                } else {
                    _status.value = Status.ERROR
                    _viewVisibility.value = View.GONE
                    _errorMessageVisibility.value = View.VISIBLE
                    _errorText.value = Constants.ErrorMessages.INTERNET_CONNECTION_ERROR
                }
            } catch (e: Exception) {
                _status.value = Status.ERROR
                _viewVisibility.value = View.GONE
                _errorMessageVisibility.value = View.VISIBLE
                _errorText.value = Constants.ErrorMessages.ERROR_MESSAGE
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
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
            _data.value?.code() == Constants.StatusCodes.UNAUTHORIZED_CODE -> {
                _status.value = Status.ERROR
                _viewVisibility.value = View.GONE
                _errorText.value = Constants.ErrorMessages.UNAUTHORIZED_MESSAGE
                _errorMessageVisibility.value = View.VISIBLE
            }
            _data.value?.code() == Constants.StatusCodes.NOT_FOUND_CODE -> {
                _status.value = Status.ERROR
                _viewVisibility.value = View.GONE
                _errorText.value = Constants.ErrorMessages.CITY_NOT_FOUND_MESSAGE
                _errorMessageVisibility.value = View.VISIBLE
            }
            _data.value?.code() == Constants.StatusCodes.OK_CODE || (_dataComingFromLoc && weatherDataDb.weatherDao()
                .getWeatherData() != null) -> {
                _status.value = Status.DONE
                _viewVisibility.value = View.VISIBLE
                _errorMessageVisibility.value = View.GONE
                setWeatherData(_data.value!!.body()!!)
                setWeatherDataToDb()
            }
        }
    }

    private fun setWeatherDataToDb() {
        if (_dataComingFromLoc && weatherDataDb.weatherDao().getWeatherData() == null) {
            weatherDataDb.weatherDao().addWeatherData(
                WeatherDataModel(
                    0,
                    _temp.value.toString(),
                    _feelsLike.value.toString(),
                    _pressure.value.toString(),
                    _humidity.value.toString(),
                    _windSpeed.value.toString(),
                    _description.value.toString(),
                    _sunrise.value.toString(),
                    _sunset.value.toString(),
                    _cityName.value.toString()
                )
            )
        }
    }

    private fun setWeatherDataFromDb() {
        _temp.value = weatherDataDb.weatherDao().getWeatherData().temp
        _feelsLike.value = weatherDataDb.weatherDao().getWeatherData().feels_like
        _pressure.value = weatherDataDb.weatherDao().getWeatherData().pressure
        _humidity.value = weatherDataDb.weatherDao().getWeatherData().humidity
        _windSpeed.value = weatherDataDb.weatherDao().getWeatherData().wind_speed
        _description.value = weatherDataDb.weatherDao().getWeatherData().description
        _sunrise.value = weatherDataDb.weatherDao().getWeatherData().sunrise
        _sunset.value = weatherDataDb.weatherDao().getWeatherData().sunset
        _cityName.value = weatherDataDb.weatherDao().getWeatherData().city_name
    }

}