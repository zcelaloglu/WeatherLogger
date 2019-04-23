package com.celaloglu.zafer.weather.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.celaloglu.zafer.weather.R

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: B

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
    }
}