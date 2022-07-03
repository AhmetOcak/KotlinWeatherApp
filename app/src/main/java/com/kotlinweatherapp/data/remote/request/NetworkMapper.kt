package com.kotlinweatherapp.data.remote.request

import com.kotlinweatherapp.data.model.*
import com.kotlinweatherapp.data.remote.response.*
import com.kotlinweatherapp.utils.mapper.EntityMapper
import javax.inject.Inject

class NetworkMapper @Inject constructor() : EntityMapper<WeatherEntity, WeatherModel> {

    override fun mapFromEntity(entity: WeatherEntity): WeatherModel {
        return WeatherModel(
            Main(
                entity.weatherData.temp,
                entity.weatherData.feelsLike,
                entity.weatherData.pressure,
                entity.weatherData.humidity
            ),
            Wind(entity.windSpeed.speed),
            listOf(Weather(entity.weatherStatus[0].description)),
            Sys(
                entity.sunTimes.sunrise,
                entity.sunTimes.sunset
            ),
            entity.cityName
        )
    }

    override fun mapToEntity(domainModel: WeatherModel): WeatherEntity {
        return WeatherEntity(
            MainE(
                domainModel.weatherData.temp,
                domainModel.weatherData.feelsLike,
                domainModel.weatherData.pressure,
                domainModel.weatherData.humidity
            ),
            WindE(domainModel.windSpeed.speed),
            listOf(WeatherE(domainModel.weatherStatus[0].description)),
            SysE(
                domainModel.sunTimes.sunrise,
                domainModel.sunTimes.sunset
            ),
            domainModel.cityName
        )
    }

}