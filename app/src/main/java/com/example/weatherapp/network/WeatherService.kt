package com.example.weatherapp.network

import com.example.weatherapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/onecall?")
    fun getCurrentWeatherData(@Query("lat") lat: String, @Query("lon") lon: String, @Query("units") units: String, @Query("APPID") app_id: String): Call<WeatherResponse>
}