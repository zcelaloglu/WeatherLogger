package com.celaloglu.zafer.weather.ui.main

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.celaloglu.zafer.weather.R
import com.celaloglu.zafer.weather.base.BaseFragment
import com.celaloglu.zafer.weather.database.Weather
import com.celaloglu.zafer.weather.databinding.FragmentMainBinding
import javax.inject.Inject

class MainFragment : BaseFragment<FragmentMainBinding>() {

    @Inject
    internal lateinit var viewModel: MainViewModel

    private lateinit var adapter: MainAdapter

    internal interface OnWeatherClickListener {
        fun onWeatherSelected(weather: Weather)
    }

    private var clickListener: OnWeatherClickListener? = null

    companion object {

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            clickListener = context as OnWeatherClickListener
        } catch (e: Exception) {
            throw ClassCastException(context.toString() +
                    " must implement MainFragment.OnWeatherClickListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentComponent.inject(this)
        setHasOptionsMenu(true)
        binding.vm = viewModel
    }

    override fun onResume() {
        super.onResume()
        observeStatus()
        observeWeathers()
        viewModel.getLocalWeathers(context)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.save, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.save) {
            viewModel.obtainWeatherForecast(context)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observeStatus() {
        viewModel.getStatus().observe(this, Observer<Status> {
            when (it) {
                Status.PROGRESS_VISIBLE -> binding.progressBar.visibility = View.VISIBLE
                Status.PROGRESS_INVISIBLE -> binding.progressBar.visibility = View.GONE
                Status.NO_NETWORK -> Toast.makeText(context,
                        R.string.no_internet_connection_message,
                        Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun observeWeathers() {
        viewModel.getWeathers().observe(this, Observer<List<Weather>> { weathers ->
            adapter = MainAdapter(this, weathers!!)
            binding.weatherRv.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }

    fun onWeatherClick(weather: Weather) {
        clickListener?.let { it.onWeatherSelected(weather) }
    }

    override fun onPause() {
        super.onPause()
        viewModel.getWeathers().removeObservers(this)
        viewModel.getStatus().removeObservers(this)
        viewModel.getStatus().value= Status.PROGRESS_INVISIBLE
    }
}