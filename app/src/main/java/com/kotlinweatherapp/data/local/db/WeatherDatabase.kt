package com.kotlinweatherapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kotlinweatherapp.data.local.db.dao.WeatherDao
import com.kotlinweatherapp.data.local.db.entity.WeatherDataModel
import com.kotlinweatherapp.utils.Constants

@Database(entities = [WeatherDataModel::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        fun getWeatherDatabase(context: Context): WeatherDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    Constants.Database.DATABASE_NAME
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}