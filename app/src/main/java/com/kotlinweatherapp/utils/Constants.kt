package com.kotlinweatherapp.utils

object Constants {
    object NetworkService {
        const val BASE_URL: String = "https://api.openweathermap.org"
        const val API_KEY: String = "YOUR API KEY"
        const val UNITS: String = "metric"
        const val END_POINT = "/data/2.5/weather/"
    }

    object StatusCodes {
        const val OK_CODE = 200
        const val NOT_FOUND_CODE = 404
        const val UNAUTHORIZED_CODE = 401
    }

    object ErrorMessages {
        const val EXCEPTION_MESSAGE = "Can not create instance of this viewModel"
        const val INTERNET_CONNECTION_ERROR = "No internet connection"
        const val ERROR_MESSAGE = "Something went wrong"
        const val CITY_NOT_FOUND_MESSAGE = "City not found"
        const val UNAUTHORIZED_MESSAGE = "Unauthorized"
    }

    object Strings {
        const val LOCATION_DATA = "locationData"
        const val CITY_NAME_NULL = "null"
        const val CITY_NAME = "cityName"
        const val REFRESH_LOC = "refresh location"
        const val PERMISSION_ID = 101
    }

    object Database {
        const val DATABASE_NAME = "weather_data.db"
    }
}