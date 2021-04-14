package com.softvision.alexlepadatu.typicode.data.db

import android.content.Context
import androidx.room.Room
import com.softvision.alexlepadatu.typicode.data.db.dao.AlbumsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    companion object {
        private const val DATABASE_NAME = "albums-db"
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AlbumsDatabase {
        return Room.databaseBuilder(appContext, AlbumsDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideChannelDao(appDatabase: AlbumsDatabase): AlbumsDao {
        return appDatabase.albumDao()
    }
}
