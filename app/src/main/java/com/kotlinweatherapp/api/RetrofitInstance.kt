package com.kotlinweatherapp.api

import com.kotlinweatherapp.data.WeatherModel
import com.kotlinweatherapp.utilities.Constants
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val api: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    suspend fun getWeatherData(cityName: String): Response<WeatherModel> {
        return api.getWeatherData(cityName, Constants.API_KEY, Constants.UNITS)
    }
}

