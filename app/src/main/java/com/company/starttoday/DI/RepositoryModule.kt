package com.company.starttoday.DI

import com.company.starttoday.Domain.Image.Repository.GetImageRepository
import com.company.starttoday.Domain.Image.Repository.UpdateImageRepository
import com.company.starttoday.Domain.Routine.Repository.DeleteRoutineRepository
import com.company.starttoday.Domain.Routine.Repository.SaveRoutineRepository
import com.company.starttoday.Domain.Routine.Repository.SetRoutineTimeRepository
import com.company.starttoday.Domain.ThingOn.Repository.GetThingOnRepository
import com.company.starttoday.Domain.ThingOn.Repository.UpdateThingOnRepository
import com.company.starttoday.data.ImageLinkData.Impl.GetImageRepositoryImpl
import com.company.starttoday.data.ImageLinkData.Impl.UpdateImageRepositoryImpl
import com.company.starttoday.data.RoutineData.Impl.DeleteRoutineRepositoryImpl
import com.company.starttoday.data.RoutineData.Impl.SaveRoutineRepositoryImpl
import com.company.starttoday.data.RoutineData.Impl.SetRoutineTimeRepositoryImpl
import com.company.starttoday.data.ThingOnData.Impl.GetThingOnRepositoryImpl
import com.company.starttoday.data.ThingOnData.Impl.UpdateThingOnRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideUpdateStringRepository(impl: UpdateThingOnRepositoryImpl): UpdateThingOnRepository = impl
    @Provides
    fun provideGetStringRepository(impl : GetThingOnRepositoryImpl) : GetThingOnRepository = impl
    @Provides
    fun provideUpdateImageRepository(impl : UpdateImageRepositoryImpl) : UpdateImageRepository = impl
    @Provides
    fun provideGetImageRepository(impl : GetImageRepositoryImpl) : GetImageRepository = impl

    @Provides
    fun provideSetRoutineTimeRepository(impl : SetRoutineTimeRepositoryImpl) : SetRoutineTimeRepository = impl

    @Provides
    fun provideSaveRoutineRepository(impl : SaveRoutineRepositoryImpl) : SaveRoutineRepository = impl

    @Provides
    fun provideDeleteRoutineRepository(impl : DeleteRoutineRepositoryImpl) : DeleteRoutineRepository = impl



}