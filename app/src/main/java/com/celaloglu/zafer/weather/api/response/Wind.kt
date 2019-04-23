package com.celaloglu.zafer.weather.api.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wind(val speed: Float,
                val deg: Int): Parcelable