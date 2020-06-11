package com.example.weatherapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.network.WeatherService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.GsonBuilder
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalField
import java.util.*

class MainActivity : AppCompatActivity() {

    // Call WeatherService interface
    private lateinit var weatherService: WeatherService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()

        // Declare retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        // Service for Retrofit with the WeatherService interface
        weatherService = retrofit.create(WeatherService::class.java)
        // Get the city, units and key of the API
        val valuesApi = weatherService.getCurrentWeatherData("35.69", "139.69","metric", "36c2237a640c8bc142e71a6b7270c6ed")

        valuesApi.enqueue(object: Callback<WeatherResponse> {

            // Handle errors, in case it's not working
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            // Fire results of the API
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>){
                val weatherResults = response.body()!!
                // Change format of the hour
                val simpleDateFormat = SimpleDateFormat("dd/MM/yyy HH:mm:ss")
                
                // Capitalizing string
                val strCurrentDescriptionWeather = weatherResults.current!!.weather[0].description
                val strUppercaseCurrentDescriptionWeather = strCurrentDescriptionWeather?.capitalize()

                // Display weather data
                /* Current data */
                textViewCityName.text = weatherResults.timezone
                //textViewCurrentTime.text = simpleDateFormat.format(weatherResults?.current?.dt)
                textViewActualTemperature.text = weatherResults.current!!.temp.toString()
                textViewMainWeather.text = weatherResults.current!!.weather[0].main
                textViewDescriptionWeather.text = strUppercaseCurrentDescriptionWeather
                textViewCurrentFeels.text = weatherResults.current!!.feels_like.toString()
                textViewCurrentUvi.text = weatherResults.current!!.uvi.toString()
                textViewCurrentHumidity.text = weatherResults.current!!.humidity.toString()
                /* Hourly data */
                //textViewTimeHourly.text = simpleDateFormat.format(weatherResults?.hourly[1].dt)
                //textViewTemperatureHourly.text = weatherResults.hourly[1].temp.toString()
                //textViewWeatherHourly.text = weatherResults.hourly[1].weather[0].main
            }
        })
    }
}
