package com.company.starttoday.DI

import com.company.starttoday.Core.Network.API.ImageLinkAPI
import com.company.starttoday.Core.Network.API.ThingOnAPI
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
    fun provideTodayStringApi() : ThingOnAPI
    {
        return Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ThingOnAPI::class.java)

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





