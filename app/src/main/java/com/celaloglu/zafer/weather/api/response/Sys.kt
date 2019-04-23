package com.celaloglu.zafer.weather.api.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sys(val type: Int,
               val id: Int,
               val message: Float,
               val country: String,
               val sunrise: Int,
               val sunset: Int): Parcelable