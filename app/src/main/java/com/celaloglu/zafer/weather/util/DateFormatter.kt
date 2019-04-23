package com.celaloglu.zafer.weather.util

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    private val DATE_FORMAT = SimpleDateFormat("E, MMM d, yyyy hh:mm a", Locale.ENGLISH)

    @JvmStatic
    fun format(inputDate: Int): String {
        val date = Date(inputDate * 1000L)
        return DATE_FORMAT.format(date).toString()
    }
}