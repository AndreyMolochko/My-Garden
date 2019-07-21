package com.ostrovec.mygarden.di

import com.ostrovec.mygarden.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule{
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}