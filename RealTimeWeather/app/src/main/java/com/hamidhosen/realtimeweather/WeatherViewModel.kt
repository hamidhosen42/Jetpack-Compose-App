package com.hamidhosen.realtimeweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidhosen.realtimeweather.api.Constant
import com.hamidhosen.realtimeweather.api.NetworkResponse
import com.hamidhosen.realtimeweather.api.RetrofitInstance
import com.hamidhosen.realtimeweather.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city: String) {

        _weatherResult.value = NetworkResponse.Loading

        viewModelScope.launch {
            try {
                val response = weatherApi.getWeather(Constant.apiKey, city)

                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                } else {
                    _weatherResult.value = NetworkResponse.Error("Failed to fetch data")
                }
            } catch (e: Exception) {
                _weatherResult.value = NetworkResponse.Error(e.message ?: "An error occurred")
            }

        }
    }
}