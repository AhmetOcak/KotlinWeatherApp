package com.kotlinweatherapp.model

data class WeatherModel(
    val main: Main,
    val wind: Wind,
    val weather: Weather,
    val sys: Sys,
)