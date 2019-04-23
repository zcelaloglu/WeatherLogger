package com.celaloglu.zafer.weather.di.module

import android.arch.lifecycle.ViewModel
import com.celaloglu.zafer.weather.di.scopes.ViewModelKey
import com.celaloglu.zafer.weather.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel
}

