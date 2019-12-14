package com.ostrovec.mygarden.ui.updateplant

import com.ostrovec.mygarden.repositories.PlantRepository
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.base.viewmodel.BaseAddUpdateViewModel
import io.reactivex.Completable
import javax.inject.Inject

class UpdatePlantViewModel @Inject constructor(val plantRepository: PlantRepository) : BaseAddUpdateViewModel() {
    fun updatePlant(plant: Plant): Completable {
        return plantRepository.updatePlant(plant)
    }

    fun updateRemotePlant(plant: Plant): Completable {
        return plantRepository.updateRemotePlant(plant)
    }
}
