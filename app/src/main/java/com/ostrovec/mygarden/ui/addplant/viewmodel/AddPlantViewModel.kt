package com.ostrovec.mygarden.ui.addplant.viewmodel

import com.ostrovec.mygarden.repositories.PlantRepository
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.base.viewmodel.BaseAddUpdateViewModel
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class AddPlantViewModel @Inject constructor(var plantRepository: PlantRepository) : BaseAddUpdateViewModel(), AddPlantViewModelType {

    override fun addPlant(plant: Plant): Flowable<Long> {
        return plantRepository.insertPlant(plant)
    }

    override fun addRemotePlant(plant: Plant): Completable {
        return plantRepository.addRemotePlant(plant)
    }
}