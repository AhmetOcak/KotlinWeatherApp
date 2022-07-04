package com.kotlinweatherapp.ui.weather

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kotlinweatherapp.R
import com.kotlinweatherapp.data.LocationData
import com.kotlinweatherapp.databinding.FragmentWeatherBinding
import com.kotlinweatherapp.data.local.db.WeatherDatabase
import com.kotlinweatherapp.utils.Status
import com.kotlinweatherapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModels()
    private var cityName: String? = null
    private lateinit var locationData: LocationData
    private lateinit var binding: FragmentWeatherBinding
    private lateinit var sharedPref: SharedPreferences

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
        cityName = requireArguments().getString(Constants.Strings.CITY_NAME)

        getLocationData()

        viewModel.setCityName(cityName)
        viewModel.setLocationData(locationData)
        viewModel.getData()

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
            Bundle().apply { putBoolean(Constants.Strings.REFRESH_LOC, true) })
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
        if (requireArguments().getSerializable(Constants.Strings.LOCATION_DATA) == null) {
            locationData = LocationData(
                sharedPref.getFloat(getString(R.string.lat_key), 0.0F).toDouble(),
                sharedPref.getFloat(getString(R.string.lon_key), 0.0F).toDouble()
            )
        } else {
            locationData =
                requireArguments().getSerializable(Constants.Strings.LOCATION_DATA) as LocationData
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