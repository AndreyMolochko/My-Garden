package com.ostrovec.mygarden.repositories

import com.ostrovec.mygarden.room.database.AppDatabase
import com.ostrovec.mygarden.room.model.ListItem
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SettingsRepositoryImp(val appDatabase: AppDatabase) : SettingsRepository {

    override fun insertListItem(listItem: ListItem): Completable {
        return Completable.fromCallable {
            appDatabase.settingsDao().insertListItem(listItem)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun loadListItems(): Flowable<List<ListItem>> {
        return appDatabase.settingsDao().getListItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateListItem(listItem: ListItem): Completable {
        return Completable.fromCallable {
            appDatabase.settingsDao().updateListItem(listItem)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}