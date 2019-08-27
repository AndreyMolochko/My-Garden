package com.ostrovec.mygarden.ui.base

import io.reactivex.subjects.BehaviorSubject

abstract class BaseAddUpdateViewModel : BaseViewModel(){
    val saveButtonClickable = BehaviorSubject.create<Boolean>()

    fun checkSaveButton(name: String, irrigation: String, photo: String) {
        saveButtonClickable.onNext(name.isNotEmpty() && irrigation.isNotEmpty() && photo.isNotEmpty())
    }
}