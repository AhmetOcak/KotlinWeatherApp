package com.kotlinweatherapp.data.model

data class WeatherModel(
    val main: Main,
    val wind: Wind,
    val weather: List<Weather>,
    val sys: Sys,
    val name: String,
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val humidity: Int,
)

data class Sys(
    val sunrise: Long,
    val sunset: Long,
)

data class Weather(
    val description: String,
)

data class Wind(
    val speed: Double,
)