package com.celaloglu.zafer.weather.di.module

import com.celaloglu.zafer.weather.BuildConfig
import com.celaloglu.zafer.weather.api.service.WeatherService
import com.celaloglu.zafer.weather.di.scopes.PerApplication
import com.celaloglu.zafer.weather.util.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    @PerApplication
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(createAuthenticationInterceptor())
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @PerApplication
    internal fun provideWeatherService(okHttpClient: OkHttpClient): WeatherService {
        val httpClientBuilder = okHttpClient.newBuilder()

        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(httpClientBuilder.build())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(WeatherService::class.java)
    }

    private fun createAuthenticationInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter(Constants.INTERCEPTOR_APPID_PARAMETER, BuildConfig.API_KEY)
                    .addQueryParameter(Constants.INTERCEPTOR_UNITS_PARAMETER, BuildConfig.UNITS)
                    .build()

            val requestBuilder = original.newBuilder()
                    .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }
}