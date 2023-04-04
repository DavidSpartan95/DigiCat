package com.example.digicat.api

import com.google.gson.annotations.SerializedName

data class WeatherData(
    val success: Boolean,
    val error: Any?,
    val response: WeatherResponse?
)

data class WeatherResponse(
    val ob: Observation?,
    val place: Place?
)

data class Observation(
    val tempC: Int
)

data class Place(
    val city: String,
    val country: String
)