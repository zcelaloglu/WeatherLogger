package com.celaloglu.zafer.weather.repository

import com.celaloglu.zafer.weather.api.response.Forecast
import com.celaloglu.zafer.weather.api.service.WeatherService
import com.celaloglu.zafer.weather.util.toDeferred
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class WeatherServiceRepositoryTest {

    private lateinit var serviceRepository: WeatherServiceRepository

    @Mock
    lateinit var weatherService: WeatherService
    @Mock
    lateinit var forecast: Forecast

    @Before
    @Throws
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        serviceRepository = WeatherServiceRepository(weatherService)
    }

    @Test
    fun should_return_forecast() {
        Mockito.`when`(serviceRepository.getWeather()).thenReturn(forecast.toDeferred())
    }
}