package com.celaloglu.zafer.weather.api.service

import com.celaloglu.zafer.weather.api.response.Forecast
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    fun getForecast(@Query("q") query: String): Deferred<Forecast>
}
