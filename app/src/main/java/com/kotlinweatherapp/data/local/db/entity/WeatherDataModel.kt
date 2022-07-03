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
    var temp: String,

    @ColumnInfo(name = "feels_like")
    var feels_like: String,

    @ColumnInfo(name = "pressure")
    var pressure: String,

    @ColumnInfo(name = "humidity")
    var humidity: String,

    @ColumnInfo(name = "speed")
    var wind_speed: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "sunrise")
    var sunrise: String,

    @ColumnInfo(name = "sunset")
    var sunset: String,

    @ColumnInfo(name = "name")
    var city_name: String,
)

