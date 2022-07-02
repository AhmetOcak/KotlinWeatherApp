package com.kotlinweatherapp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kotlinweatherapp.R
import com.kotlinweatherapp.data.LocationData
import com.kotlinweatherapp.utilities.WeatherViewModelFactory
import com.kotlinweatherapp.databinding.FragmentWeatherBinding
import com.kotlinweatherapp.db.WeatherDatabase
import com.kotlinweatherapp.utilities.Status
import com.kotlinweatherapp.viewmodels.WeatherViewModel
import com.kotlinweatherapp.utilities.Constants

class WeatherFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var cityName: String
    private lateinit var locationData: LocationData
    private lateinit var binding: FragmentWeatherBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var weatherDataDb: WeatherDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackHandler()

        sharedPref = activity?.getSharedPreferences("location_data", Context.MODE_PRIVATE)!!
        cityName = requireArguments().getString(Constants.CITY_NAME) ?: Constants.CITY_NAME_NULL
        weatherDataDb = WeatherDatabase.getWeatherDatabase(requireContext())!!

        getLocationData()

        val weatherViewModelFactory =
            WeatherViewModelFactory(cityName, locationData, weatherDataDb)
        viewModel =
            ViewModelProvider(this, weatherViewModelFactory)[WeatherViewModel::class.java]

        binding.viewModel = viewModel
        binding.fragmentWeather = this
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

    fun refreshLocation() {
        findNavController().navigate(
            R.id.action_weatherFragment_to_getLocationFragment,
            Bundle().apply { putBoolean(Constants.REFRESH_LOC, true) })
    }

    private fun onBackHandler() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                ActivityCompat.finishAffinity(requireActivity())
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun getLocationData() {
        if (requireArguments().getSerializable(Constants.LOCATION_DATA) == null) {
            locationData = LocationData(
                sharedPref.getFloat(getString(R.string.lat_key), 0.0F).toDouble(),
                sharedPref.getFloat(getString(R.string.lon_key), 0.0F).toDouble()
            )
        } else {
            locationData =
                requireArguments().getSerializable(Constants.LOCATION_DATA) as LocationData
            setLocDataToSharPref()
        }
    }

    private fun setLocDataToSharPref() {
        with(sharedPref.edit()) {
            putFloat(getString(R.string.lat_key), locationData.latitude.toFloat())
            putFloat(getString(R.string.lon_key), locationData.longitude.toFloat())
            apply()
        }
    }
}