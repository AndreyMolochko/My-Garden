package com.ostrovec.mygarden.di

import android.app.Application
import com.ostrovec.mygarden.MyGardenApp
import com.ostrovec.mygarden.di.modules.ActivityModule
import com.ostrovec.mygarden.di.modules.DatabaseModule
import com.ostrovec.mygarden.di.modules.NetworkModule
import com.ostrovec.mygarden.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    ActivityModule::class,
    AndroidSupportInjectionModule::class,
    ViewModelModule::class,
    DatabaseModule::class,
    NetworkModule::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(appController: MyGardenApp)
}