package com.celaloglu.zafer.weather.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.celaloglu.zafer.weather.util.Constants
import kotlinx.android.parcel.Parcelize

@Entity(tableName = Constants.WEATHER_TABLE_NAME)
@Parcelize
data class Weather(@PrimaryKey
                   @ColumnInfo(name = "date")
                   val date: Int,
                   @ColumnInfo(name = "temp")
                   val temp: Int,
                   @ColumnInfo(name = "tempMin")
                   val tempMin: Int,
                   @ColumnInfo(name = "tempMax")
                   val tempMax: Int,
                   @ColumnInfo(name = "pressure")
                   val pressure: Int,
                   @ColumnInfo(name = "humidity")
                   val humidity: Int,
                   @ColumnInfo(name = "iconLink")
                   val iconLink: String): Parcelable