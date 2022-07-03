package com.kotlinweatherapp.ui.location

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GetLocationViewModel: ViewModel() {

    private val _progressBarVisibility = MutableLiveData(View.GONE)
    val progressBarVisibility: LiveData<Int> get() = _progressBarVisibility

    fun setProgressBarVisibility(visibility: Int) {
        _progressBarVisibility.value = visibility
    }
}