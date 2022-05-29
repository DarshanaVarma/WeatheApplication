package com.example.weatherapp.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.weatherapp.R
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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )

        binding.mainViewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.getWeatherData()

        viewModel.weatherData.observe(this, Observer {
            Log.d(TAG, "movieList: $it")
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