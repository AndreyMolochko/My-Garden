package com.ostrovec.mygarden.ui.addplant

import com.ostrovec.mygarden.repositories.PlantRepository
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.base.BaseAddUpdateViewModel
import javax.inject.Inject

class AddPlantViewModel @Inject constructor(var plantRepository: PlantRepository) : BaseAddUpdateViewModel
() {

    fun addPlant(plant: Plant) {
        plantRepository.insertPlant(plant).also { compositeDisposable.add(it) }
    }
}