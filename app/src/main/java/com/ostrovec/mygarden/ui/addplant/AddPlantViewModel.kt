package com.ostrovec.mygarden.ui.addplant

import com.ostrovec.mygarden.repositories.PlantRepository
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.base.BaseAddUpdateViewModel
import io.reactivex.Completable
import javax.inject.Inject

class AddPlantViewModel @Inject constructor(var plantRepository: PlantRepository) : BaseAddUpdateViewModel
() {

    fun addPlant(plant: Plant) {
        plantRepository.insertPlant(plant).also { compositeDisposable.add(it) }
    }

    fun addRemotePlant(plant: Plant):Completable{
        return plantRepository.addRemotePlant(plant)
    }
}