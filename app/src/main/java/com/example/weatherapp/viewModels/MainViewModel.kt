package com.example.weatherapp.viewModels

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.weatherapp.model.WeatherModelClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(private val repository: MainRepository) : ViewModel(), Observable {

    val weatherData = MutableLiveData<WeatherModelClass>()
    val errorMessage = MutableLiveData<String>()

   /* @Bindable*/
    val text = MutableLiveData<String>()
    val tempC = MutableLiveData<String>()
    val tempF = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val region = MutableLiveData<String>()
    val country = MutableLiveData<String>()
    val url = MutableLiveData<String>()

    fun getWeatherData() {
        val response = repository.getAllMovies()
        response.enqueue(object : Callback<WeatherModelClass> {
            override fun onResponse(
                call: Call<WeatherModelClass>,
                response: Response<WeatherModelClass>
            ) {
                weatherData.postValue(response.body())
                text.value = response.body()?.current?.condition?.text
                tempC.value =
                    "Temperaure in degree celcius " + response.body()?.current?.tempC.toString()
                tempF.value =
                    "Temperaure in degree farenheight " + response.body()?.current?.tempF?.toString()
                name.value = response.body()?.location?.name
                region.value = response.body()?.location?.region
                country.value = response.body()?.location?.country
                url.value = "https://" + response.body()?.current?.condition?.icon

            }

            override fun onFailure(call: Call<WeatherModelClass>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    @BindingAdapter("profileImage")
    fun loadImage(view: ImageView, imageUrl: String?) {
        Glide.with(view.getContext())
            .load(imageUrl).apply(RequestOptions().circleCrop())
            .into(view)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}