package com.kotlinweatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.kotlinweatherapp.R
import com.kotlinweatherapp.api.RetrofitInstance
import com.kotlinweatherapp.data.WeatherModel
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var model: Response<WeatherModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            model = RetrofitInstance.getWeatherData("ankara")
            Log.e("e", model.body().toString())
        }
    }
}