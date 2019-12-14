package com.ostrovec.mygarden.ui.base.viewmodel

import io.reactivex.subjects.BehaviorSubject

abstract class BaseAddUpdateViewModel : BaseViewModel(), BaseAddUpdateViewModelType{
    val saveButtonClickable = BehaviorSubject.create<Boolean>()

    override fun checkSaveButton(name: String, irrigation: String, photo: String) {
        saveButtonClickable.onNext(name.isNotEmpty() && irrigation.isNotEmpty() && photo.isNotEmpty())
    }
}