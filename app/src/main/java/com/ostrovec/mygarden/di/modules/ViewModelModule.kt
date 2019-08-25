package com.ostrovec.mygarden.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ostrovec.mygarden.di.ViewModelFactory
import com.ostrovec.mygarden.di.ViewModelKey
import com.ostrovec.mygarden.ui.addplant.AddPlantViewModel
import com.ostrovec.mygarden.ui.myplants.MyPlantsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AddPlantViewModel::class)
    protected abstract fun addPlantViewModel(addPlantViewModel: AddPlantViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyPlantsViewModel::class)
    protected abstract fun myPlantsViewModel(myPlantsViewModel: MyPlantsViewModel):ViewModel

}