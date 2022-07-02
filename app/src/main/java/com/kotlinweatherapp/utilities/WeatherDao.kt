package com.kotlinweatherapp.utilities

import androidx.room.*
import com.kotlinweatherapp.data.model.WeatherDataModel

@Dao
interface WeatherDao {

    @Insert
    fun addWeatherData(weatherData: WeatherDataModel)

    @Query("SELECT * FROM weather_data")
    fun getWeatherData(): WeatherDataModel

    @Update
    fun updateWeatherData(weatherData: WeatherDataModel)
}