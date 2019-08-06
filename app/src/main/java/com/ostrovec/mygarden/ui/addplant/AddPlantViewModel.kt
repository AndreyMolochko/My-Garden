package com.ostrovec.mygarden.ui.addplant

import com.ostrovec.mygarden.ui.base.BaseViewModel
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject


class AddPlantViewModel @Inject constructor() : BaseViewModel() {
    val saveButtonClickable = BehaviorSubject.create<Boolean>()

    fun checkSaveButton(name: String, irrigation: String, photo: String) {
        saveButtonClickable.onNext(name.isNotEmpty() && irrigation.isNotEmpty() && photo.isNotEmpty())
    }
}