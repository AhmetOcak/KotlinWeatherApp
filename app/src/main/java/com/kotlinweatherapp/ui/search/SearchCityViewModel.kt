package com.kotlinweatherapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class SearchCityViewModel: ViewModel() {

    val currentText = MutableLiveData<String>()

    var isCanGo = Transformations.map(currentText) {
        !currentText.value.isNullOrEmpty()
    }
}