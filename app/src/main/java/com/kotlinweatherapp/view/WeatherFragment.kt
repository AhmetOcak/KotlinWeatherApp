package com.kotlinweatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kotlinweatherapp.R
import com.kotlinweatherapp.databinding.FragmentWeatherBinding
import com.kotlinweatherapp.utilities.Status
import com.kotlinweatherapp.viewmodels.WeatherViewModel
import com.kotlinweatherapp.data.WeatherViewModelFactory


class WeatherFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var cityName: String
    private lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityName = requireArguments().getString("cityName") ?: "Washington"

        val weatherViewModelFactory =
            WeatherViewModelFactory(cityName)
        viewModel =
            ViewModelProvider(this, weatherViewModelFactory).get(WeatherViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.searchButton.setOnClickListener {
            goToNextScreen()
        }

        viewModel.status.observe(viewLifecycleOwner) {
            setProgressBarVisibility()
        }
    }

    private fun setProgressBarVisibility() {
        if (viewModel.status.value == Status.LOADING) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun goToNextScreen() {
        findNavController().navigate(R.id.action_weatherFragment_to_searchCityFragment)
    }
}