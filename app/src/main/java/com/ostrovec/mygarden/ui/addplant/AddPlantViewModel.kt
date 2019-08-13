package com.ostrovec.mygarden.ui.addplant

import com.ostrovec.mygarden.repositories.PlantRepository
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.base.BaseViewModel
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject


class AddPlantViewModel @Inject constructor(var plantRepository: PlantRepository) : BaseViewModel
() {
    val saveButtonClickable = BehaviorSubject.create<Boolean>()

    fun checkSaveButton(name: String, irrigation: String, photo: String) {
        saveButtonClickable.onNext(name.isNotEmpty() && irrigation.isNotEmpty() && photo.isNotEmpty())
    }

    fun addPlant(plant: Plant) {
        plantRepository.insertPlant(plant).also { compositeDisposable.add(it) }
    }
}