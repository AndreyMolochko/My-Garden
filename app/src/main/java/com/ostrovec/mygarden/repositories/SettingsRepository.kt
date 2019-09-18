package com.ostrovec.mygarden.repositories

import com.ostrovec.mygarden.room.model.ListItem
import io.reactivex.Completable
import io.reactivex.Flowable


interface SettingsRepository {
    fun insertListItem(listItem: ListItem): Completable

    fun getTitleItems(): Flowable<out List<ListItem>>

    fun getLanguageItems(): Flowable<out List<ListItem>>

    fun getSwitchItems(): Flowable<out List<ListItem>>

    fun updateListItem(listItem: ListItem): Completable
}