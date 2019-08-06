package com.ostrovec.mygarden.di.modules

import com.ostrovec.mygarden.ui.addplant.AddPlantActivity
import com.ostrovec.mygarden.ui.guide.GuideActivity
import com.ostrovec.mygarden.ui.myplants.MyPlantsActivity
import com.ostrovec.mygarden.ui.welcome.WelcomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule{
    @ContributesAndroidInjector
    abstract fun contributeWelcomeActivity(): WelcomeActivity

    @ContributesAndroidInjector
    abstract fun contributeGuideActivity(): GuideActivity

    @ContributesAndroidInjector
    abstract fun contributeMyPlantsActivity(): MyPlantsActivity

    @ContributesAndroidInjector
    abstract fun contributeAddPlantsActivity(): AddPlantActivity
}