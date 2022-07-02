package com.kotlinweatherapp.data.repo

import androidx.lifecycle.MutableLiveData
import com.kotlinweatherapp.api.RetrofitInstance
import com.kotlinweatherapp.data.LocationData
import com.kotlinweatherapp.data.model.WeatherModel
import retrofit2.Response
import java.lang.Exception
import java.net.UnknownHostException

object WeatherRepository {

    private val data = MutableLiveData<Response<WeatherModel>>()

    suspend fun getWeatherDataWithCityName(cityName: String): Response<WeatherModel> {
        try {
            data.value = RetrofitInstance.getWeatherDataWithCityName(cityName)
            return data.value!!
        } catch (e: UnknownHostException) {
            throw e
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getWeatherDataWithLocation(locationData: LocationData): Response<WeatherModel> {
        try {
            data.value = RetrofitInstance.getWeatherDataWithLocation(locationData)
            return data.value!!
        } catch (e: UnknownHostException) {
            throw e
        } catch (e: Exception) {
            throw e
        }
    }
}