package com.ostrovec.mygarden.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.ostrovec.mygarden.room.model.LanguageItem
import com.ostrovec.mygarden.room.model.ListItem
import com.ostrovec.mygarden.room.model.SwitchItem
import com.ostrovec.mygarden.room.model.TitleItem
import io.reactivex.Flowable

@Dao
interface SettingsDao {

    @Insert(onConflict = REPLACE)
    fun insertTitleItem(titleItem: TitleItem)

    @Insert(onConflict = REPLACE)
    fun insertSwitchItem(switchItem: SwitchItem)

    @Insert(onConflict = REPLACE)
    fun insertLanguageItem(languageItem: LanguageItem)

    @Query("SELECT * FROM SettingsTitle")
    fun getTitlesItems(): Flowable<List<TitleItem>>

    @Query("SELECT * FROM SettingsLanguage")
    fun getLanguagesItems(): Flowable<List<LanguageItem>>

    @Query("SELECT * FROM SettingsSwitch")
    fun getSwitchesItems(): Flowable<List<SwitchItem>>

    @Update(onConflict = REPLACE)
    fun updateLanguageItem(languageItem: LanguageItem)

    @Update(onConflict = REPLACE)
    fun updateSwitchItem(switchItem: SwitchItem)
}