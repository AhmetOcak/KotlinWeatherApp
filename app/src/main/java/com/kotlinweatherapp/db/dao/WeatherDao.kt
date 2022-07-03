package com.kotlinweatherapp.db.dao

import androidx.room.*
import com.kotlinweatherapp.db.entity.WeatherDataModel

@Dao
interface WeatherDao {

    @Insert
    fun addWeatherData(weatherData: WeatherDataModel)

    @Query("SELECT * FROM weather_data")
    fun getWeatherData(): WeatherDataModel

    @Update
    fun updateWeatherData(weatherData: WeatherDataModel)
}