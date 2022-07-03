package com.kotlinweatherapp.data.local.db.dao

import androidx.room.*
import com.kotlinweatherapp.data.local.db.entity.WeatherDataModel

@Dao
interface WeatherDao {

    @Insert
    fun addWeatherData(weatherData: WeatherDataModel)

    @Query("SELECT * FROM weather_data")
    fun getWeatherData(): WeatherDataModel

    @Update
    fun updateWeatherData(weatherData: WeatherDataModel)
}