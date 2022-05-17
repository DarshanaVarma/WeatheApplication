package com.example.weatherapp.viewModels

import com.example.weatherapp.connection.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllMovies() = retrofitService.getAllMovies()
}