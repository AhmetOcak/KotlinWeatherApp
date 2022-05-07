package com.kotlinweatherapp.data

data class WeatherModel(
    val main: Main,
    val wind: Wind,
    val weather: List<Weather>,
    val sys: Sys,
    val name: String,
)