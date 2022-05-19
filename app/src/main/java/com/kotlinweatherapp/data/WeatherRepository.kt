package com.kotlinweatherapp.data

import androidx.lifecycle.MutableLiveData
import com.kotlinweatherapp.api.RetrofitInstance
import retrofit2.Response
import java.lang.Exception
import java.net.UnknownHostException

class WeatherRepository {

    private val data = MutableLiveData<Response<WeatherModel>>()

    suspend fun getWeatherData(cityName: String): Response<WeatherModel> {
        try {
            data.value = RetrofitInstance.getWeatherData(cityName)
            return data.value!!
        } catch (e: UnknownHostException) {
            throw e
        } catch (e: Exception) {
            throw e
        }
    }
}