package com.celaloglu.zafer.weather.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("imgSrc")
    fun setImageUrl(view: ImageView, imgSrc: String?) {
        Glide.with(view.context).load(imgSrc).into(view)
    }
}