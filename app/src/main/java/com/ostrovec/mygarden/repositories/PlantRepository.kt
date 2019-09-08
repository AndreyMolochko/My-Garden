package com.ostrovec.mygarden.repositories

import com.ostrovec.mygarden.room.model.Plant
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface PlantRepository {

    fun insertPlant(plant: Plant): Flowable<Long>

    fun loadPlants(): Flowable<List<Plant>>

    fun updatePlant(plant: Plant): Completable

    fun deletePlant(plant: Plant): Completable

    fun addRemotePlant(plant: Plant): Completable

    fun deleteRemotePlant(plantId: Long): Completable

    fun updateRemotePlant(plant: Plant): Completable

    fun loadRemotePlants()
}