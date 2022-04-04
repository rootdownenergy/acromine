package com.rootdown.dev.adidev_albertson.di

import android.content.Context
import androidx.room.Room
import com.rootdown.dev.adidev_albertson.data.local.AcromineDao
import com.rootdown.dev.adidev_albertson.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAcromineDao(appDatabase: AppDatabase): AcromineDao {
        return appDatabase.acromineDao()
    }


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "acromine"
        ).build()
    }
}