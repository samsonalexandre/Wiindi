package com.example.wiindi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wiindi.adapters.WeatherModel

class MainViewModel: ViewModel() {
    val liveDataCurrent = MutableLiveData<WeatherModel>()
    val liveDataList = MutableLiveData<List<WeatherModel>>()

}