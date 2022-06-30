package com.kotlinweatherapp.data.weathermodel

data class WeatherModel(
    val main: Main,
    val wind: Wind,
    val weather: List<Weather>,
    val sys: Sys,
    val name: String,
)