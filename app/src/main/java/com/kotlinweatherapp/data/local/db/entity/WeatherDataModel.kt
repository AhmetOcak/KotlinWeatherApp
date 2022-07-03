package com.kotlinweatherapp.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_data")
data class WeatherDataModel (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "temp")
    var temp: Double,

    @ColumnInfo(name = "feels_like")
    var feels_like: Double,

    @ColumnInfo(name = "pressure")
    var pressure: Double,

    @ColumnInfo(name = "humidity")
    var humidity: Int,

    @ColumnInfo(name = "speed")
    var wind_speed: Double,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "sunrise")
    var sunrise: Long,

    @ColumnInfo(name = "sunset")
    var sunset: Long,

    @ColumnInfo(name = "name")
    var city_name: String,
)

