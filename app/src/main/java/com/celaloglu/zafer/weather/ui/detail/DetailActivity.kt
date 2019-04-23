package com.celaloglu.zafer.weather.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.celaloglu.zafer.weather.R
import com.celaloglu.zafer.weather.base.BaseActivity
import com.celaloglu.zafer.weather.database.Weather
import com.celaloglu.zafer.weather.databinding.ActivityDetailBinding
import com.celaloglu.zafer.weather.util.Constants
import com.celaloglu.zafer.weather.util.DateFormatter
import com.celaloglu.zafer.weather.util.inTransaction

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.inTransaction {
            val weather = intent.getParcelableExtra<Weather>(Constants.INTENT_EXTRA)
            supportActionBar?.title = DateFormatter.format(weather!!.date)
            add(R.id.detailContainer, DetailFragment.newInstance(weather))
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_detail
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return true
    }

    companion object {

        fun start(context: Context, weather: Weather) {
            Intent(context, DetailActivity::class.java)
                    .apply { putExtra(Constants.INTENT_EXTRA, weather) }
                    .also { context.startActivity(it) }
        }
    }
}