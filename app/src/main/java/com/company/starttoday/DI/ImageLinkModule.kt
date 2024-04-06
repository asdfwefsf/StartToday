package com.company.starttoday.DI

import android.content.Context
import androidx.room.Room
import com.company.starttoday.data.ImageLinkData.Room.ImageLinkDao
import com.company.starttoday.data.ImageLinkData.Room.ImageLinkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImageLinkModule {

    @Provides
    @Singleton
    fun provideImageLinkDatabase(@ApplicationContext appContext : Context) : ImageLinkDatabase {
        return Room.databaseBuilder(
            appContext,
            ImageLinkDatabase::class.java,
            "image_link_database"
        ).build()

    }

    @Provides
    fun provideImageLinkDato(database: ImageLinkDatabase) : ImageLinkDao {
        return database.dao
    }

//    @Provides
//    @Singleton
//    fun provideImageLinkImpl(
//        imageLinkAPI : ImageLinkAPI,
//        dao : ImageLinkDao
//    ) : ImageLinkImpl = ImageLinkImpl(imageLinkAPI , dao)



}