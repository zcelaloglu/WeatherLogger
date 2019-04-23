package com.celaloglu.zafer.weather.ui.detail

import android.os.Bundle
import android.view.View
import com.celaloglu.zafer.weather.R
import com.celaloglu.zafer.weather.base.BaseFragment
import com.celaloglu.zafer.weather.database.Weather
import com.celaloglu.zafer.weather.databinding.FragmentDetailBinding
import com.celaloglu.zafer.weather.util.Constants

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weather = arguments!!.getParcelable<Weather>(Constants.INTENT_EXTRA)
        binding.item = weather
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_detail
    }

    companion object {

        fun newInstance(weather: Weather): DetailFragment {
            return DetailFragment()
                    .apply {
                        arguments = Bundle(1)
                                .apply { putParcelable(Constants.INTENT_EXTRA, weather) }
                    }
        }
    }
}