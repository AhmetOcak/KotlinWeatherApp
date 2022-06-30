package com.kotlinweatherapp.data

import androidx.room.*

@Dao
interface WeatherDao {

    @Insert
    fun addWeatherData(weatherData: WeatherDataModel)

    @Query("SELECT * FROM weather_data")
    fun getWeatherData(): WeatherDataModel

    @Update
    fun updateWeatherData(weatherData: WeatherDataModel)
}