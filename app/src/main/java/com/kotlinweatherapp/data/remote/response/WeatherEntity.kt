package com.kotlinweatherapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class WeatherEntity(

    @SerializedName("main")
    val weatherData: MainE,

    @SerializedName("wind")
    val windSpeed: WindE,

    @SerializedName("weather")
    val weatherStatus: List<WeatherE>,

    @SerializedName("sys")
    val sunTimes: SysE,

    @SerializedName("name")
    val cityName: String,
)

data class MainE(

    @SerializedName("temp")
    val temp: Double,

    @SerializedName("feels_like")
    val feelsLike: Double,

    @SerializedName("pressure")
    val pressure: Double,

    @SerializedName("humidity")
    val humidity: Int,
)

data class WindE(

    @SerializedName("speed")
    val speed: Double,
)

data class SysE(

    @SerializedName("sunrise")
    val sunrise: Long,

    @SerializedName("sunset")
    val sunset: Long,
)

data class WeatherE(

    @SerializedName("description")
    val description: String,
)
