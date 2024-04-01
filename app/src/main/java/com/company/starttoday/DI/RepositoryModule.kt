package com.company.starttoday.DI

import com.company.starttoday.Data.Impl.GetImageRepositoryImpl
import com.company.starttoday.Data.Impl.GetStringRepositoryImpl
import com.company.starttoday.Data.Impl.StringRepositoryImpl
import com.company.starttoday.Repository.GetImageRepository
import com.company.starttoday.Repository.GetStringRepository
import com.company.starttoday.Repository.UpdateStringRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideStringRepository(impl: StringRepositoryImpl): UpdateStringRepository = impl

    @Provides
    fun provideGetStringRepository(impl : GetStringRepositoryImpl) : GetStringRepository = impl

    @Provides
    fun provideGetImageRepository(impl : GetImageRepositoryImpl) : GetImageRepository = impl

}