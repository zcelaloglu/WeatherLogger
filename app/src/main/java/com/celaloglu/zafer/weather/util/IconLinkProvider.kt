package com.celaloglu.zafer.weather.util

object IconLinkProvider {

    fun getIconLink(icon: String): String {
        return "http://openweathermap.org/img/w/$icon.png"
    }
}