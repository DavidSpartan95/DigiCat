package com.example.digicat.api

import retrofit2.Call
import retrofit2.http.GET

interface WeatherApiService {
    @GET("observations/stockholm,se")
    fun getWeatherData(): Call<WeatherData>
}