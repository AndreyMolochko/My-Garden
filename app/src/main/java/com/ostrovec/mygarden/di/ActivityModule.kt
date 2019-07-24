package com.ostrovec.mygarden.di

import com.ostrovec.mygarden.ui.guide.GuideActivity
import com.ostrovec.mygarden.ui.welcome.WelcomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule{
    @ContributesAndroidInjector
    abstract fun contributeWelcomeActivity(): WelcomeActivity

    @ContributesAndroidInjector
    abstract fun contributeGuideActivity(): GuideActivity
}