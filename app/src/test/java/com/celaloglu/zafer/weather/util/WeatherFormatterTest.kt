package com.celaloglu.zafer.weather.util

import android.content.Context
import com.celaloglu.zafer.weather.R
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class WeatherFormatterTest {

    @Mock
    lateinit var context: Context

    @Before
    @Throws
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun test_celsius_formatter() {
        val expected = "10°C"
        doReturn(expected)
                .`when`(context)
                .getString(R.string.celsius_formatter)
        val actual = WeatherFormatter.formatTemperate(context, 10)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun test_pressure_formatter() {
        val expected = "1502 hPa"
        doReturn(expected)
                .`when`(context)
                .getString(R.string.pressure_formatter)
        val actual = WeatherFormatter.formatPressure(context, 1502)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun test_humidity_formatter() {
        val expected = "54"
        doReturn(expected)
                .`when`(context)
                .getString(R.string.humidity_formatter)
        val actual = WeatherFormatter.formatHumidity(context, 54)
        Assert.assertEquals(expected, actual)
    }
}