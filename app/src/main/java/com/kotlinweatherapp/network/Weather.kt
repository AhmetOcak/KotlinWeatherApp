package com.kotlinweatherapp.network

data class WeatherModel(
    val main: Main,
    val wind: Wind,
    val weather: Weather,
    val sys: SYS,
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val humidity: Int,
)

data class Weather(
    val description: String,
)

data class Wind(
    val speed: Double,
)

data class SYS(
    val sunrise: Long,
    val sunset: Long,
)