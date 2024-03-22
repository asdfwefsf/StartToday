package com.company.starttoday.DI

import android.content.Context
import androidx.room.Room
import com.company.starttoday.Core.Network.API.StringAPI
import com.company.starttoday.Data.Impl.StringRepositoryImpl
import com.company.starttoday.Data.ThingOnData.ThingOnDao
import com.company.starttoday.Data.ThingOnData.ThingOnDatabase
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
        stringApi : StringAPI,
        dao : ThingOnDao
    ) : StringRepositoryImpl = StringRepositoryImpl(stringApi , dao)

//    @Provides
//    @Singleton
//    fun provideStringRepositoryImpl(
//        stringAPI: StringAPI,
//        dao: ThingOnDao
//    ): StringRepositoryImpl {
//        return StringRepositoryImpl(stringAPI, dao)
//    }

}