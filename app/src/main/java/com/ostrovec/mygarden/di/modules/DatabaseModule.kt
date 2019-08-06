package com.ostrovec.mygarden.di.modules

import android.app.Application
import androidx.room.Room
import com.ostrovec.mygarden.room.dao.PlantDao
import com.ostrovec.mygarden.room.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
                .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providePlantDao(appDatabase: AppDatabase): PlantDao {
        return appDatabase.plantDao()
    }
}