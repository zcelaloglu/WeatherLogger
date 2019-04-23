package com.celaloglu.zafer.weather.util

import android.content.Context
import com.celaloglu.zafer.weather.R

object WeatherFormatter {

    @JvmStatic
    fun formatTemperate(context: Context, temperature: Int): String {
        return String.format(context.getString(R.string.celsius_formatter), temperature)
    }

    @JvmStatic
    fun formatPressure(context: Context, pressure: Int): String {
        return String.format(context.getString(R.string.pressure_formatter), pressure)
    }

    @JvmStatic
    fun formatHumidity(context: Context, humidity: Int): String {
        return String.format(context.getString(R.string.humidity_formatter), humidity)
    }
}