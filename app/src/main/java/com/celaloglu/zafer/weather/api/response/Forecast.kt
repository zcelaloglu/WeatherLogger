package com.celaloglu.zafer.weather.api.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Forecast(val weather: @RawValue List<Weather>,
                    val base: String,
                    val main: @RawValue Main,
                    val visibility: Int,
                    val wind: @RawValue Wind,
                    val dt: Int,
                    val sys: @RawValue Sys,
                    val id: Int,
                    val name: String,
                    val cod: Int) : Parcelable