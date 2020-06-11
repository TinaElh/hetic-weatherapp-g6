package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

class WeatherResponse {

    var lat: Float = 0.toFloat()
    var lon: Float = 0.toFloat()
    var timezone: String? = null

    @SerializedName("current")
    var current: Current? = null
    @SerializedName("weather")
    var weather = ArrayList<Weather>()
    @SerializedName("hourly")
    var hourly = ArrayList<Hourly>()
    @SerializedName("daily")
    var daily = ArrayList<Daily>()
}

// Current weather data API response
class Current {
    @SerializedName("dt")
    var dt: Int? = null
    @SerializedName("sunrise")
    var sunrise: Int? = null
    @SerializedName("sunset")
    var sunset: Int? = null
    @SerializedName("temp")
    var temp: Float = 0.toFloat()
    @SerializedName("feels_like")
    var feels_like: Float = 0.toFloat()
    @SerializedName("humidity")
    var humidity: Int? = null
    @SerializedName("uvi")
    var uvi: Float = 0.toFloat()
    @SerializedName("wind_speed")
    var wind_speed: Float = 0.toFloat()
    @SerializedName("weather")
    var weather = ArrayList<Weather>()
}

// Array weather
class Weather {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("main")
    var main: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("icon")
    var icon: String? = null
}

// Array of hourly forecast weather data API response
class Hourly {
    @SerializedName("dt")
    var dt: Int? = null
    @SerializedName("temp")
    var temp: Float = 0.toFloat()
    // There is the array "weather" in the array "hourly" which contains the weather data for the hour
    @SerializedName("weather")
    var weather = ArrayList<Weather>()
}

// Array of daily forecast weather data API response
class Daily {
    @SerializedName("dt")
    var dt: Int? = null
    @SerializedName("sunrise")
    var sunrise: Int? = null
    @SerializedName("sunset")
    var sunset: Int? = null
    @SerializedName("humidity")
    var humidity: Int? = null
    // There is the array "weather" in the array "daily" which contains the weather data for the day
    @SerializedName("weather")
    var weather = ArrayList<Weather>()
}