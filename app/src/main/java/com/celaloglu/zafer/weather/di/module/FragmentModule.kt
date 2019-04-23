package com.celaloglu.zafer.weather.di.module

import com.celaloglu.zafer.weather.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributeMainFragment(): MainFragment
}