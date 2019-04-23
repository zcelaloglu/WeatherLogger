package com.celaloglu.zafer.weather

import android.app.Application
import android.support.v4.app.Fragment
import com.celaloglu.zafer.weather.di.component.AppComponent
import com.celaloglu.zafer.weather.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class WeatherApplication : Application(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
        appComponent.inject(this)
    }

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }
}