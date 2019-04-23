package com.celaloglu.zafer.weather.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Main(val temp: Float,
                val pressure: Int,
                val humidity: Int,
                @SerializedName("temp_min")
                val tempMin: Float,
                @SerializedName("temp_max")
                val tempMax: Float): Parcelable