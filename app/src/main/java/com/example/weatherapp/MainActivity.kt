package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.network.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var weatherService: WeatherService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        weatherService = retrofit.create(WeatherService::class.java)
        val valuesApi = weatherService.getCurrentWeatherData("35.69", "139.69", "36c2237a640c8bc142e71a6b7270c6ed")

        valuesApi.enqueue(object: Callback<WeatherResponse> {

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>){
                val results = response.body()!!
                println(results)

                /*val stringBuilder = "Country: " +
                        results.sys!!.country +
                        "\n" +
                        "Temperature: " +
                        results.main!!.temp +
                        "\n" +
                        "Temperature(Min): " +
                        results.main!!.temp_min +
                        "\n" +
                        "Temperature(Max): " +
                        results.main!!.temp_max +
                        "\n" +
                        "Humidity: " +
                        results.main!!.humidity +
                        "\n" +
                        "Pressure: " +
                        results.main!!.pressure

                println(stringBuilder)*/
            }
        })
    }
}
