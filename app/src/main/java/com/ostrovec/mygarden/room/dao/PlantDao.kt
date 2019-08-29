package com.ostrovec.mygarden.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.ostrovec.mygarden.room.model.Plant
import io.reactivex.Flowable

@Dao
interface PlantDao {

    @Insert(onConflict = REPLACE)
    fun insertPlant(plant: Plant)

    @Query("SELECT * FROM Plants")
    fun getPlants(): Flowable<List<Plant>>

    @Update(onConflict = REPLACE)
    fun updatePlant(plant:Plant)
}