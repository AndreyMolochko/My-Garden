package com.ostrovec.mygarden.repositories

import com.ostrovec.mygarden.room.model.Plant
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable

interface PlantRepository {

    fun insertPlant(plant: Plant): Disposable

    fun loadPlants(): Flowable<List<Plant>>

    fun updatePlant(plant: Plant): Completable

    fun deletePlant(plant: Plant): Completable

    fun addRemotePlant(plant: Plant): Completable

    fun deleteRemotePlant(plantId: Int): Completable

    fun updateRemotePlant(plant: Plant): Completable

    fun loadRemotePlants(): Flowable<List<Plant>>
}