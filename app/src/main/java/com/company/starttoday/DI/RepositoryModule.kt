package com.company.starttoday.DI

import com.company.starttoday.Data.Impl.StringRepositoryImpl
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
}