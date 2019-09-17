package com.ostrovec.mygarden.repositories

import com.ostrovec.mygarden.room.database.AppDatabase
import com.ostrovec.mygarden.room.model.LanguageItem
import com.ostrovec.mygarden.room.model.ListItem
import com.ostrovec.mygarden.room.model.SwitchItem
import com.ostrovec.mygarden.room.model.TitleItem
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SettingsRepositoryImp(val appDatabase: AppDatabase) : SettingsRepository {

    override fun insertListItem(listItem: ListItem): Completable {
        return Completable.fromCallable {
            when (listItem) {
                is TitleItem -> appDatabase.settingsDao().insertTitleItem(listItem)
                is LanguageItem -> appDatabase.settingsDao().insertLanguageItem(listItem)
                is SwitchItem -> appDatabase.settingsDao().insertSwitchItem(listItem)
            }
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getTitleItems(): Flowable<List<TitleItem>> {
        return appDatabase.settingsDao().getTitlesItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getLanguageItems(): Flowable<List<LanguageItem>> {
        return appDatabase.settingsDao().getLanguagesItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getSwitchItems(): Flowable<List<SwitchItem>> {
        return appDatabase.settingsDao().getSwitchesItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateListItem(listItem: ListItem): Completable {
        return Completable.fromCallable {
            when (listItem) {
                is LanguageItem -> appDatabase.settingsDao().updateLanguageItem(listItem)
                is SwitchItem -> appDatabase.settingsDao().updateSwitchItem(listItem)
            }
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}