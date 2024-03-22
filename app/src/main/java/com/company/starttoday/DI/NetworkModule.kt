package com.company.starttoday.DI

import com.company.starttoday.Core.Network.API.ImageLinkAPI
import com.company.starttoday.Core.Network.API.StringAPI
import com.company.starttoday.Core.Network.API.WeatherAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideWeatherApi() : WeatherAPI
    {
        return Retrofit.Builder()
            .baseUrl("http://apis.data.go.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)

    }

    @Provides
    fun provideTodayStringApi() : StringAPI
    {
        return Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StringAPI::class.java)

    }

    @Provides
    fun provideImageLinkApi() : ImageLinkAPI
    {
        return Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImageLinkAPI::class.java)
    }

}





