package com.ostrovec.mygarden.ui.myplants.viewmodel

import com.ostrovec.mygarden.repositories.PlantRepository
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.base.viewmodel.BaseViewModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class MyPlantsViewModel @Inject constructor(var plantsRepository: PlantRepository) : BaseViewModel(),MyPlantsViewModelType {

    fun getPlants(): Flowable<List<Plant>> {
        return plantsRepository.loadPlants()
    }

    fun deletePlant(plant: Plant): Completable {
        return plantsRepository.deletePlant(plant)
    }

    fun deleteRemotePlant(taskId: Long): Completable {
        return plantsRepository.deleteRemotePlant(taskId)
    }

    fun getRemotePlants(): Observable<List<Plant>> {
        return plantsRepository.loadRemotePlants()
    }
}