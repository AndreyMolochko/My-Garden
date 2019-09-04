package com.ostrovec.mygarden.di.modules

import com.google.gson.Gson
import com.ostrovec.mygarden.repositories.AuthRepository
import com.ostrovec.mygarden.repositories.AuthRepositoryImp
import com.ostrovec.mygarden.repositories.PlantRepository
import com.ostrovec.mygarden.repositories.PlantRepositoryImp
import com.ostrovec.mygarden.room.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun providePlantRepository(appDatabase: AppDatabase): PlantRepository {
        return PlantRepositoryImp(appDatabase)
    }

    @Provides
    fun provideAuthRepository(): AuthRepository{
        return AuthRepositoryImp()
    }
}