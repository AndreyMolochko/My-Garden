package com.ostrovec.mygarden.repositories

import com.ostrovec.mygarden.room.model.LanguageItem
import com.ostrovec.mygarden.room.model.ListItem
import com.ostrovec.mygarden.room.model.SwitchItem
import com.ostrovec.mygarden.room.model.TitleItem
import io.reactivex.Completable
import io.reactivex.Flowable


interface SettingsRepository {
    fun insertListItem(listItem: ListItem): Completable

    fun getTitleItems():Flowable<List<TitleItem>>

    fun getLanguageItems(): Flowable<List<LanguageItem>>

    fun getSwitchItems():Flowable<List<SwitchItem>>

    fun updateListItem(listItem: ListItem): Completable
}