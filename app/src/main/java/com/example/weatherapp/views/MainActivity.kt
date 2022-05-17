package com.example.weatherapp.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.weatherapp.viewModels.MainRepository
import com.example.weatherapp.viewModels.MainViewModel
import com.example.weatherapp.viewModels.MyViewModelFactory
import com.example.weatherapp.connection.RetrofitService
import com.example.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity :::"
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )
        viewModel.getWeatherData()

        viewModel.weatherData.observe(this, Observer {
            Log.d(TAG, "movieList: $it")
            binding.text.text =  it.current?.condition?.text
            binding.tempC.text ="Temperaure in degree celcius "+ it.current?.tempC.toString()
            binding.tempF.text = "Temperaure in degree farenheight "+it.current?.tempF?.toString()
            binding.name.text = it.location?.name
            binding.region.text = it.location?.region
            binding.country.text = it.location?.country

            val media = "https://" + it.current?.condition?.icon
            Glide.with(this)
                .load(media)
                .into(binding.image)
        })

        viewModel.errorMessage.observe(this, Observer {
            Log.d(TAG, "errorMessage: $it")
        })
    }
}