package com.celaloglu.zafer.weather.database

import android.arch.persistence.room.Room
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class DatabaseTest {

    private lateinit var database: WeatherDatabase
    private lateinit var weatherDao: WeatherDao

    @Mock
    lateinit var context: Context

    @Before
    @Throws
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        database = Room.inMemoryDatabaseBuilder(context, WeatherDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        weatherDao = database.weatherDao()
    }

    @Test
    fun test_insert() {
        val givenInt = ArgumentMatchers.any(Int::class.java)
        val weather = Weather(givenInt, givenInt, givenInt, givenInt, givenInt, givenInt)
        CoroutineScope(Dispatchers.IO).launch {
            weatherDao.insert(weather)
            assertThat(weatherDao.getWeathers().count(), `is`(1))
        }
    }
}