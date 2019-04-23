package com.celaloglu.zafer.weather.repository

import com.celaloglu.zafer.weather.api.response.Forecast
import com.celaloglu.zafer.weather.api.service.WeatherService
import com.celaloglu.zafer.weather.util.Constants
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class WeatherServiceRepository @Inject constructor(private val service: WeatherService) {

    fun getWeather(): Deferred<Forecast> {
        return service.getForecast(Constants.QUERY)
    }
}