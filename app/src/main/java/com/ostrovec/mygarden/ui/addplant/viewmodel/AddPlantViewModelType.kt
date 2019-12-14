package com.ostrovec.mygarden.ui.addplant.viewmodel

import com.ostrovec.mygarden.room.model.Plant
import io.reactivex.Completable
import io.reactivex.Flowable

interface AddPlantViewModelType {
    fun addPlant(plant: Plant): Flowable<Long>

    fun addRemotePlant(plant: Plant): Completable
}