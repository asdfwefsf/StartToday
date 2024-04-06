package com.company.starttoday.DI

import android.content.Context
import androidx.room.Room
import com.company.starttoday.data.RoutineData.Room.RoutineDao
import com.company.starttoday.data.RoutineData.Room.RoutineDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoutineModule {

    @Provides
    @Singleton
    fun provideRoutineDatabase(@ApplicationContext appContext : Context) : RoutineDatabase {
        return Room.databaseBuilder(
            appContext,
            RoutineDatabase::class.java,
            "routine_database"
        ).build()
    }

    @Provides
    fun provideRoutineDao(database: RoutineDatabase) : RoutineDao {
        return database.dao
    }

}