package com.company.starttoday.DI

import android.content.Context
import com.company.starttoday.Core.Network.API.WeatherAPI
import com.company.starttoday.Data.Impl.forecastRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ForecastModule {

    @Provides
    @Singleton
    fun provideForecastRepositoryImpl(
        weatherAPI: WeatherAPI,
        context: Context
    ) : forecastRepositoryImpl = forecastRepositoryImpl(weatherAPI , context)




}