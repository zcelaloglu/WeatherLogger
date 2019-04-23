package com.celaloglu.zafer.weather.api.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(val id: Int,
                   val main: String,
                   val description: String,
                   val icon: String): Parcelable