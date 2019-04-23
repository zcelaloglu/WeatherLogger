package com.celaloglu.zafer.weather.di.component

import com.celaloglu.zafer.weather.di.module.ViewModelModule
import com.celaloglu.zafer.weather.di.scopes.PerFragment
import com.celaloglu.zafer.weather.ui.main.MainFragment
import dagger.Component

@PerFragment
@Component(dependencies = [(AppComponent::class)],
        modules = [(ViewModelModule::class)])
interface FragmentComponent {
    fun inject(fragment: MainFragment)
}