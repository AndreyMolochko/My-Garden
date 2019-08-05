package com.ostrovec.mygarden.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ostrovec.mygarden.room.model.Plant
import io.reactivex.Flowable

@Dao
interface PlantDao {

    @Insert
    fun insertPlant(plant: Plant)

    @Query("SELECT * FROM Plants")
    fun getPlants(): Flowable<List<Plant>>
}