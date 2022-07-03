package com.kotlinweatherapp.api

import com.kotlinweatherapp.data.LocationData
import com.kotlinweatherapp.data.model.WeatherModel
import com.kotlinweatherapp.utils.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val api: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.NetworkService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    suspend fun getWeatherDataWithCityName(cityName: String): Response<WeatherModel> {
        return api.getWeatherDataWithCityName(
            cityName,
            Constants.NetworkService.API_KEY,
            Constants.NetworkService.UNITS
        )
    }

    suspend fun getWeatherDataWithLocation(locationData: LocationData): Response<WeatherModel> {
        return api.getWeatherDataWithLocation(
            locationData.latitude,
            locationData.longitude,
            Constants.NetworkService.API_KEY,
            Constants.NetworkService.UNITS
        )
    }
}

