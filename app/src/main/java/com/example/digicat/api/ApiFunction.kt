package com.example.digicat.api

import com.example.digicat.viewModel.TemperatureViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

val temperatureViewModel = TemperatureViewModel()
fun fetchWeather() = CoroutineScope(Dispatchers.IO).launch {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url("https://aerisweather1.p.rapidapi.com/observations/tokyo,jp")
        .get()
        .addHeader("X-RapidAPI-Key", "e9e5801793mshbe7f6cd37cfb663p12d233jsna1c5a4abb5ca")
        .addHeader("X-RapidAPI-Host", "aerisweather1.p.rapidapi.com")
        .build()

    val response = client.newCall(request).execute()
    val json = response.body()?.string()

    val gson = Gson()
    val weatherData = gson.fromJson(json, WeatherData::class.java)

    temperatureViewModel.setTemp(weatherData.response?.ob?.tempC.toString())
    println(temperatureViewModel.temp.value)
}
