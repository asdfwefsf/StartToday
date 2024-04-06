package com.company.starttoday.DI

import android.content.Context
import androidx.room.Room
import com.company.starttoday.data.AlarmData.Room.AlarmDao
import com.company.starttoday.data.AlarmData.Room.AlarmDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AlarmModule {

    @Provides
    @Singleton
    fun provideAlarmDatabase(@ApplicationContext appContext: Context): AlarmDatabase {
        return Room.databaseBuilder(
            appContext,
            AlarmDatabase::class.java,
            "alarm_database"
        ).build()
    }

    @Provides
    fun provideAlarmDao(database: AlarmDatabase) : AlarmDao {
        return database.dao
    }

//    @Provides
//    @Singleton
//    fun provideAndroidAlarmScheduler(context : Context) : AndroidAlarmScheduler {
//        return AndroidAlarmScheduler(context)
//    }




//    @Provides
//    @Singleton
//    fun provideMediaPlayerManager(@ApplicationContext context: Context): MediaPlayerManager {
//        return MediaPlayerManager(context)
//    }
}

