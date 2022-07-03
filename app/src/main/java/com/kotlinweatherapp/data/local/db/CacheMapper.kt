package com.kotlinweatherapp.data.local.db

import com.kotlinweatherapp.data.local.db.entity.WeatherDataModel
import com.kotlinweatherapp.data.model.*
import com.kotlinweatherapp.data.remote.response.WeatherE
import com.kotlinweatherapp.utils.mapper.EntityMapper
import javax.inject.Inject

class CacheMapper @Inject constructor() : EntityMapper<WeatherDataModel, WeatherModel>{

    override fun mapFromEntity(entity: WeatherDataModel): WeatherModel {
        return WeatherModel(
            Main(
                entity.temp,
                entity.feels_like,
                entity.pressure,
                entity.humidity
            ),
            Wind(entity.wind_speed),
            listOf(Weather(entity.description)),
            Sys(
                entity.sunrise,
                entity.sunset
            ),
            entity.city_name
        )
    }

    override fun mapToEntity(domainModel: WeatherModel): WeatherDataModel {
        return WeatherDataModel(
            temp = domainModel.weatherData.temp,
            feels_like = domainModel.weatherData.feelsLike,
            pressure = domainModel.weatherData.pressure,
            humidity = domainModel.weatherData.humidity,
            wind_speed = domainModel.windSpeed.speed,
            description = domainModel.weatherStatus[0].description,
            sunrise = domainModel.sunTimes.sunrise,
            sunset = domainModel.sunTimes.sunset,
            city_name = domainModel.cityName
        )
    }
}