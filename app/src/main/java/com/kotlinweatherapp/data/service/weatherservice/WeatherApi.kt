package com.kotlinweatherapp.data.service.weatherservice

import com.kotlinweatherapp.data.remote.response.WeatherEntity
import com.kotlinweatherapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(Constants.NetworkService.END_POINT)
    suspend fun getWeatherDataWithCityName(
        @Query("q") cityName: String,
        @Query("APPID") apikey: String,
        @Query("units") units: String,
    ): Response<WeatherEntity>

    @GET(Constants.NetworkService.END_POINT)
    suspend fun getWeatherDataWithLocation(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("APPID") apiKey: String,
        @Query("units") units: String,
    ): Response<WeatherEntity>
}