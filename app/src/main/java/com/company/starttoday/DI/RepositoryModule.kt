package com.company.starttoday.DI

import com.company.starttoday.Data.ImageLinkData.Impl.GetImageRepositoryImpl
import com.company.starttoday.Data.ThingOnData.Impl.GetThingOnRepositoryImpl
import com.company.starttoday.Data.ThingOnData.Impl.UpdateThingOnRepositoryImpl
import com.company.starttoday.Domain.Image.Repository.GetImageRepository
import com.company.starttoday.Domain.ThingOn.Repository.GetThingOnRepository
import com.company.starttoday.Domain.ThingOn.Repository.UpdateThingOnRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideStringRepository(impl: UpdateThingOnRepositoryImpl): UpdateThingOnRepository = impl

    @Provides
    fun provideGetStringRepository(impl : GetThingOnRepositoryImpl) : GetThingOnRepository = impl

    @Provides
    fun provideGetImageRepository(impl : GetImageRepositoryImpl) : GetImageRepository = impl

}