package com.kotlinweatherapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlinweatherapp.data.local.db.dao.WeatherDao
import com.kotlinweatherapp.data.local.db.entity.WeatherDataModel

@Database(entities = [WeatherDataModel::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

}