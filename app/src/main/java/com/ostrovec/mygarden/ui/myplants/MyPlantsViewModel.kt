package com.ostrovec.mygarden.ui.myplants

import com.ostrovec.mygarden.repositories.PlantRepository
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.base.BaseViewModel
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class MyPlantsViewModel @Inject constructor(var plantsRepository: PlantRepository) : BaseViewModel() {

    fun getPlants(): Flowable<List<Plant>> {
        return plantsRepository.loadPlants()
    }

    fun deletePlant(plant: Plant): Completable {
        return plantsRepository.deletePlant(plant)
    }

    fun deleteRemotePlant(taskId: Long): Completable {
        return plantsRepository.deleteRemotePlant(taskId)
    }
}