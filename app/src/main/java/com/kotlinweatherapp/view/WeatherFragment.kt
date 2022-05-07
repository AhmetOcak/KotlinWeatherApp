package com.kotlinweatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kotlinweatherapp.databinding.FragmentWeatherBinding
import com.kotlinweatherapp.viewmodels.WeatherViewModel


class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWeatherBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

}