package com.ostrovec.mygarden.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ostrovec.mygarden.room.dao.PlantDao
import com.ostrovec.mygarden.room.database.AppDatabase.Companion.VERSION
import com.ostrovec.mygarden.room.model.Plant

@Database(entities = [Plant::class],version = VERSION,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object{
        const val DB_NAME = "plants.db"
        const val VERSION = 1
    }

    abstract fun plantDao(): PlantDao
}