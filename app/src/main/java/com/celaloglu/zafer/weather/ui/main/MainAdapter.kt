package com.celaloglu.zafer.weather.ui.main

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import com.celaloglu.zafer.weather.database.Weather
import com.celaloglu.zafer.weather.databinding.ItemWeatherBinding
import java.lang.ref.WeakReference

class MainAdapter(fragment: MainFragment, private val weathers: List<Weather>) :
        RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val fragment = WeakReference(fragment)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWeatherBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding, fragment)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val weather = weathers[position]
        holder.bind(weather)
    }

    override fun getItemCount(): Int {
        return weathers.size
    }

    class MainViewHolder(private val binding: ViewDataBinding,
                         private val fragment: WeakReference<MainFragment>)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Weather) {
            binding.setVariable(BR.item, item)
            binding.setVariable(BR.view, fragment.get())
            binding.executePendingBindings()
        }
    }
}