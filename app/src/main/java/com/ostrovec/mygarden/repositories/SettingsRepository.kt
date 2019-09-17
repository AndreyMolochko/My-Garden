package com.ostrovec.mygarden.repositories

import com.ostrovec.mygarden.room.model.ListItem
import io.reactivex.Completable
import io.reactivex.Flowable


interface SettingsRepository {
    fun insertListItem(listItem: ListItem): Completable

    fun loadListItems(): Flowable<List<ListItem>>

    fun updateListItem(listItem: ListItem): Completable
}