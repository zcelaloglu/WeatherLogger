package com.celaloglu.zafer.weather.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.celaloglu.zafer.weather.database.Weather
import com.celaloglu.zafer.weather.database.WeatherDatabase
import com.celaloglu.zafer.weather.repository.WeatherServiceRepository
import com.celaloglu.zafer.weather.util.IconLinkProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(private var serviceRepository: WeatherServiceRepository)
    : ViewModel() {

    private val status = MutableLiveData<Status>()
    private val weathers = MutableLiveData<List<Weather>>()

    fun getStatus(): MutableLiveData<Status> {
        return status
    }

    fun getWeathers(): LiveData<List<Weather>> {
        return weathers
    }

    fun obtainWeatherForecast(context: Context?) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                withContext(Dispatchers.Main) {
                    status.value = Status.PROGRESS_VISIBLE
                }
                val response = serviceRepository.getWeather().await()
                val weather = Weather(response.dt, response.main.temp.toInt(), response.main.tempMin.toInt(),
                        response.main.tempMax.toInt(), response.main.pressure, response.main.humidity,
                        IconLinkProvider.getIconLink(response.weather[0].icon))
                persistToDb(context, weather)
            } catch (e: Throwable) {
                withContext(Dispatchers.Main) {
                    status.value = Status.PROGRESS_INVISIBLE
                    status.value = Status.NO_NETWORK
                }
            }
        }
    }

    private fun persistToDb(context: Context?, weather: Weather) {
        CoroutineScope(Dispatchers.IO).launch {
            WeatherDatabase.getDatabase(context!!).weatherDao().insert(weather)
            val localWeathers = WeatherDatabase.getDatabase(context).weatherDao().getWeathers()
            withContext(Dispatchers.Main) {
                weathers.value = localWeathers
                status.value = Status.PROGRESS_INVISIBLE
            }
        }
    }

    fun getLocalWeathers(context: Context?) {
        CoroutineScope(Dispatchers.IO).launch {
            val localWeathers = WeatherDatabase.getDatabase(context!!).weatherDao().getWeathers()
            withContext(Dispatchers.Main) {
                weathers.value = localWeathers
            }
        }
    }
}