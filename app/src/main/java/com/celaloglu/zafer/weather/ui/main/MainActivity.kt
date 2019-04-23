package com.celaloglu.zafer.weather.ui.main

import android.os.Bundle
import com.celaloglu.zafer.weather.R
import com.celaloglu.zafer.weather.base.BaseActivity
import com.celaloglu.zafer.weather.database.Weather
import com.celaloglu.zafer.weather.databinding.ActivityMainBinding
import com.celaloglu.zafer.weather.ui.detail.DetailActivity
import com.celaloglu.zafer.weather.ui.detail.DetailFragment
import com.celaloglu.zafer.weather.util.inTransaction

class MainActivity : BaseActivity<ActivityMainBinding>(), MainFragment.OnWeatherClickListener {

    private var isTwoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isTwoPane = binding.detailContainer != null
        supportFragmentManager.inTransaction {
            add(R.id.container, MainFragment.newInstance())
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onWeatherSelected(weather: Weather) {
        if (isTwoPane) {
            supportFragmentManager.inTransaction {
                add(R.id.detailContainer, DetailFragment.newInstance(weather))
            }
        } else {
            DetailActivity.start(this, weather)
        }
    }
}
