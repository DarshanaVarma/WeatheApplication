package com.example.weatherapp.connection

import com.example.weatherapp.model.WeatherModelClass
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("v1/forecast.json?key=8e58902208da4c8daea185417221105&q=Pune&days=1&aqi=no&alerts=no")
    fun getAllMovies(): Call<WeatherModelClass>

    companion object {

        var retrofitService: RetrofitService? = null

      //Create the Retrofit service instance using the retrofit.
        fun getInstance(): RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://api.weatherapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}