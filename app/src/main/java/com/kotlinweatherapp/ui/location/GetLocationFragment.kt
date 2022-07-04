package com.kotlinweatherapp.ui.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.*
import com.kotlinweatherapp.R
import com.kotlinweatherapp.data.LocationData
import com.kotlinweatherapp.databinding.FragmentGetLocationBinding
import com.kotlinweatherapp.data.local.db.WeatherDatabase
import com.kotlinweatherapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.system.exitProcess

@AndroidEntryPoint
class GetLocationFragment : Fragment() {

    private lateinit var binding: FragmentGetLocationBinding
    @Inject lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val viewModel: GetLocationViewModel by viewModels()
    private lateinit var locationData: LocationData
    @Inject lateinit var weatherDataDb: WeatherDatabase
    private var refreshPage: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetLocationBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshPage = requireArguments().getBoolean(Constants.Strings.REFRESH_LOC)
    }

    override fun onStart() {
        super.onStart()
        getLastLocation()
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            when {
                isLocationEnabled() -> {
                    mFusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
                        val location: Location? = task.result
                        if (location == null) {
                            requestNewLocationData()
                            viewModel.setProgressBarVisibility(View.VISIBLE)
                            getLastLocation()
                        } else {
                            viewModel.setProgressBarVisibility(View.VISIBLE)
                            locationData = LocationData(location.latitude, location.longitude)
                            goToNextScreenWithLocData()
                        }
                    }
                }
                weatherDataDb.weatherDao().getWeatherData() == null || refreshPage -> {
                    Toast.makeText(requireContext(), "Turn on location", Toast.LENGTH_LONG).show()
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(intent)
                }
                !refreshPage -> {
                    goToNextScreen()
                }
            }
        } else {
            requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        Looper.myLooper()?.let {
            mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                it
            )
        }
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            Constants.Strings.PERMISSION_ID
        )
        getLastLocation()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == Constants.Strings.PERMISSION_ID) {
            if (!(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                requireActivity().finish()
                exitProcess(0)
            }
        }
    }

    private fun goToNextScreenWithLocData() {
        findNavController().navigate(
            R.id.action_getLocationFragment_to_weatherFragment,
            Bundle().apply { putSerializable(Constants.Strings.LOCATION_DATA, locationData) })
    }

    private fun goToNextScreen() {
        findNavController().navigate(R.id.action_getLocationFragment_to_weatherFragment)
    }
}