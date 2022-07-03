package com.kotlinweatherapp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kotlinweatherapp.R
import com.kotlinweatherapp.databinding.FragmentSearchCityBinding

class SearchCityFragment : Fragment() {

    private lateinit var binding: FragmentSearchCityBinding
    private val viewModel: SearchCityViewModel by viewModels()
    private var isCanGo = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchCityBinding.inflate(LayoutInflater.from(context))
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.isCanGo.observe(viewLifecycleOwner) {
            isCanGo = it
        }

        binding.imageButton.setOnClickListener {
            goNextScreen()
        }
        binding.findCityButton.setOnClickListener {
            if (isCanGo) {
                searchCity()
            }
        }
        return binding.root
    }

    private fun goNextScreen() {
        findNavController().navigate(R.id.action_searchCityFragment_to_weatherFragment)
    }

    private fun searchCity() {
        findNavController().navigate(
            R.id.action_searchCityFragment_to_weatherFragment,
            Bundle().apply { putString("cityName", viewModel.currentText.value.toString()) })
    }
}