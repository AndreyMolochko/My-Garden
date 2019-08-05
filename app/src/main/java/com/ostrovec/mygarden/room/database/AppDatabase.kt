package com.ostrovec.mygarden.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ostrovec.mygarden.room.dao.PlantDao
import com.ostrovec.mygarden.room.model.Plant

@Database(entities = [Plant::class],version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao
}