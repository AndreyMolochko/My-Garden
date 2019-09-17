package com.ostrovec.mygarden.repositories

import com.ostrovec.mygarden.ui.settings.model.ListItem
import io.reactivex.Completable
import io.reactivex.Flowable

class SettingsRepositoryImp :SettingsRepository{
    override fun insertListItem(listItem: ListItem): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadListItems(): Flowable<List<ListItem>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateListItem(listItem: ListItem): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}