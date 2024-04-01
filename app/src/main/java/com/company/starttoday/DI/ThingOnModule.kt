package com.company.starttoday.DI

import android.content.Context
import androidx.room.Room
import com.company.starttoday.Data.ThingOnData.Impl.UpdateThingOnRepositoryImpl
import com.company.starttoday.Data.ThingOnData.Room.ThingOnDao
import com.company.starttoday.Data.ThingOnData.Room.ThingOnDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ThingOnModule {
    @Provides
    @Singleton
    fun provideThingOnDatabase(@ApplicationContext appContext: Context): ThingOnDatabase {
        return Room.databaseBuilder(
            appContext,
            ThingOnDatabase::class.java,
            "thing_on_database"
        ).build()
    }


    @Provides
    fun provideStringsDao(database: ThingOnDatabase) : ThingOnDao {
        return database.dao
    }

    @Provides
    @Singleton
    fun provideStringRepoditoryImpl(
        dao : ThingOnDao
    ) : UpdateThingOnRepositoryImpl = UpdateThingOnRepositoryImpl(dao)


}