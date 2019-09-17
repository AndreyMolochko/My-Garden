package com.ostrovec.mygarden.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.ostrovec.mygarden.room.model.ListItem
import io.reactivex.Flowable

@Dao
interface SettingsDao {

    @Insert(onConflict = REPLACE)
    fun insertListItem(listItem: ListItem)

    @Query("SELECT * FROM Settings")
    fun getListItems(): Flowable<List<ListItem>>

    @Update(onConflict = REPLACE)
    fun updateListItem(listItem: ListItem)
}