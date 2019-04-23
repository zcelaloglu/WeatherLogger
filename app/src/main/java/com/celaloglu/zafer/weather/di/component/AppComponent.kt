package com.celaloglu.zafer.weather.di.component

import android.app.Application
import com.celaloglu.zafer.weather.api.service.WeatherService
import com.celaloglu.zafer.weather.di.module.NetworkModule
import com.celaloglu.zafer.weather.di.scopes.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@PerApplication
@Component(modules = [AndroidInjectionModule::class, NetworkModule::class])
interface AppComponent : AppComponentProvides {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: Application)
}

interface AppComponentProvides {
    fun weatherService(): WeatherService
}
