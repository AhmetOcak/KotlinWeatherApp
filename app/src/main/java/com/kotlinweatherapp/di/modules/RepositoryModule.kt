package com.kotlinweatherapp.di.modules

import com.kotlinweatherapp.data.local.db.CacheMapper
import com.kotlinweatherapp.data.local.db.dao.WeatherDao
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
        //weatherDao: WeatherDao,
        weatherApi: WeatherApi,
        networkMapper: NetworkMapper,
        //cacheMapper: CacheMapper
    ): WeatherRepository {
        return WeatherRepository(
            //weatherDao,
            weatherApi,
            networkMapper,
            //cacheMapper
        )
    }
}