package com.kotlinweatherapp.di.modules

import android.content.Context
import androidx.room.Room
import com.kotlinweatherapp.data.local.db.WeatherDatabase
import com.kotlinweatherapp.data.local.db.dao.WeatherDao
import com.kotlinweatherapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideWeatherDb(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            Constants.Database.DATABASE_NAME
        )
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherDAO(weatherDatabase: WeatherDatabase) : WeatherDao {
        return weatherDatabase.weatherDao()
    }
}