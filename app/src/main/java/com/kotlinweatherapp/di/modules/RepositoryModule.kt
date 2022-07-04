package com.kotlinweatherapp.di.modules

import com.kotlinweatherapp.data.remote.request.NetworkMapper
import com.kotlinweatherapp.data.remote.request.WeatherRepository
import com.kotlinweatherapp.data.service.weatherservice.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        weatherApi: WeatherApi,
        networkMapper: NetworkMapper,
    ): WeatherRepository {
        return WeatherRepository(
            weatherApi,
            networkMapper,
        )
    }
}