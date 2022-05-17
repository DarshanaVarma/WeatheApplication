package com.example.weatherapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.WeatherModelClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val weatherData = MutableLiveData<WeatherModelClass>()
    val errorMessage = MutableLiveData<String>()

    fun getWeatherData() {
        val response = repository.getAllMovies()
        response.enqueue(object : Callback<WeatherModelClass> {
            override fun onResponse(call: Call<WeatherModelClass>, response: Response<WeatherModelClass>) {
                weatherData.postValue(response.body())
            }

            override fun onFailure(call: Call<WeatherModelClass>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}