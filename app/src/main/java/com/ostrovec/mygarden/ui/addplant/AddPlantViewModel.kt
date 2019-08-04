package com.ostrovec.mygarden.ui.addplant

import androidx.lifecycle.ViewModel
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject


class AddPlantViewModel @Inject constructor() : ViewModel() {
    val saveButtonClickable = BehaviorSubject.create<Boolean>()

    fun checkSaveButton(name: String, irrigation: String, photo: String) {
        saveButtonClickable.onNext(name.isNotEmpty() && irrigation.isNotEmpty() && photo.isNotEmpty())
    }
}