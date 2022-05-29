package com.example.weatherapp.model

import com.example.weatherapp.model.Current
import com.example.weatherapp.model.Location
import com.google.gson.annotations.SerializedName


data class WeatherModelClass (

    @SerializedName("location" ) var location : Location? = Location(),
    @SerializedName("current"  ) var current  : Current?  = Current(),
//  @SerializedName("forecast" ) var forecast : Forecast? = Forecast()
//
//

)