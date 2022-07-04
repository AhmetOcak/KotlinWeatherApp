package com.kotlinweatherapp.data.remote.request

import com.kotlinweatherapp.data.LocationData
import com.kotlinweatherapp.data.model.WeatherModel
import com.kotlinweatherapp.data.service.weatherservice.WeatherApi
import com.kotlinweatherapp.utils.Constants
import java.lang.Exception
import java.net.UnknownHostException

class WeatherRepository(
    private val weatherApi: WeatherApi,
    private val networkMapper: NetworkMapper,
) {
    suspend fun getWeatherDataWithCityName(cityName: String): WeatherModel? {
        try {
            val weatherData = weatherApi.getWeatherDataWithCityName(
                cityName,
                Constants.NetworkService.API_KEY,
                Constants.NetworkService.UNITS
            )
            StatusCode.statusCode = weatherData.code()
            return weatherData.body()?.let { networkMapper.mapFromEntity(it) }
        } catch (e: UnknownHostException) {
            throw e
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getWeatherDataWithLocation(locationData: LocationData): WeatherModel? {
        try {
            val weatherData = weatherApi.getWeatherDataWithLocation(
                locationData.latitude,
                locationData.longitude,
                Constants.NetworkService.API_KEY,
                Constants.NetworkService.UNITS
            )
            StatusCode.statusCode = weatherData.code()
            return weatherData.body()?.let { networkMapper.mapFromEntity(it) }
        } catch (e: UnknownHostException) {
            throw e
        } catch (e: Exception) {
            throw e
        }
    }
}