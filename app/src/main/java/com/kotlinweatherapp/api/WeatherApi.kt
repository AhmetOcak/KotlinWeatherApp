package com.kotlinweatherapp.api

import com.kotlinweatherapp.data.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/data/2.5/weather/")
    suspend fun getWeatherData(
        @Query("q") cityName: String,
        @Query("APPID") apikey: String,
        @Query("units") units: String,
    ): Response<WeatherModel>
}