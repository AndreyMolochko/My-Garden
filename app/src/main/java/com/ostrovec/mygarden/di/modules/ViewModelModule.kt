package com.ostrovec.mygarden.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ostrovec.mygarden.di.ViewModelFactory
import com.ostrovec.mygarden.di.ViewModelKey
import com.ostrovec.mygarden.ui.addplant.viewmodel.AddPlantViewModel
import com.ostrovec.mygarden.ui.myplants.viewmodel.MyPlantsViewModel
import com.ostrovec.mygarden.ui.settings.viewmodel.SettingsViewModel
import com.ostrovec.mygarden.ui.sign.signin.viewmodel.SignInViewModel
import com.ostrovec.mygarden.ui.sign.signup.viewmodel.SignUpViewModel
import com.ostrovec.mygarden.ui.updateplant.viewmodel.UpdatePlantViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(UpdatePlantViewModel::class)
    protected abstract fun updatePlantViewModel(updatePlantViewModel: UpdatePlantViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    protected abstract fun signUpViewModel(signUpViewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    protected abstract fun signInViewModel(signInViewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    protected abstract fun settingsViewModel(settingsViewModel: SettingsViewModel): ViewModel

}