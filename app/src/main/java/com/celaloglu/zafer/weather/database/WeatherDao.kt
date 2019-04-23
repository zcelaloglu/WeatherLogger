package com.celaloglu.zafer.weather.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.database.Cursor
import com.celaloglu.zafer.weather.util.Constants

@Dao
interface WeatherDao {

    @Query("SELECT * from forecast")
    fun getWeathers(): List<Weather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weather: Weather)

    @Query("SELECT * FROM " + Constants.WEATHER_TABLE_NAME)
    fun selectAll(): Cursor
}