package com.kotlinweatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kotlinweatherapp.R
import com.kotlinweatherapp.databinding.FragmentWeatherBinding
import com.kotlinweatherapp.viewmodels.WeatherViewModel
import com.kotlinweatherapp.viewmodels.WeatherViewModelFactory


class WeatherFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var cityName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWeatherBinding.inflate(inflater)
        cityName = requireArguments().getString("cityName") ?: "Ankara"

        val weatherViewModelFactory =
            WeatherViewModelFactory(cityName)
        viewModel = ViewModelProvider(this, weatherViewModelFactory).get(WeatherViewModel::class.java)


        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.searchButton.setOnClickListener {
            goToNextScreen()
        }
        return binding.root
    }

    private fun goToNextScreen() {
        findNavController().navigate(R.id.action_weatherFragment_to_searchCityFragment)
    }
}