package com.kotlinweatherapp.api

import com.kotlinweatherapp.data.weathermodel.WeatherModel
import com.kotlinweatherapp.utilities.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(Constants.END_POINT)
    suspend fun getWeatherDataWithCityName(
        @Query("q") cityName: String,
        @Query("APPID") apikey: String,
        @Query("units") units: String,
    ): Response<WeatherModel>

    @GET(Constants.END_POINT)
    suspend fun getWeatherDataWithLocation(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("APPID") apiKey: String,
        @Query("units") units: String,
    ): Response<WeatherModel>
}