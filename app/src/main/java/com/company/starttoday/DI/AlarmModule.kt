package com.company.starttoday.DI

import android.app.AlarmManager
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

    @Provides
    fun provideAlarmManager(@ApplicationContext context: Context): AlarmManager {
        return context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

}

