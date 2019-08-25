package com.ostrovec.mygarden.repositories

import com.ostrovec.mygarden.room.model.Plant
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable

interface PlantRepository{

    fun insertPlant(plant: Plant): Disposable

    fun loadPlants(): Flowable<List<Plant>>
}