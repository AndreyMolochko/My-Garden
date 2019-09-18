package com.ostrovec.mygarden.ui.settings

import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.repositories.SettingsRepository
import com.ostrovec.mygarden.room.model.LanguageItem
import com.ostrovec.mygarden.room.model.ListItem
import com.ostrovec.mygarden.room.model.SwitchItem
import com.ostrovec.mygarden.room.model.TitleItem
import com.ostrovec.mygarden.ui.base.BaseViewModel
import com.ostrovec.mygarden.ui.settings.model.Languages
import com.ostrovec.mygarden.ui.settings.model.Notifications
import io.reactivex.Flowable
import javax.inject.Inject

class SettingsViewModel @Inject constructor(var settingsRepository: SettingsRepository) : BaseViewModel() {

    fun getSettings():Flowable<List<ListItem>>{
        var titlesList: Flowable<out List<ListItem>> = settingsRepository.getTitleItems()
        var languagesList: Flowable<out List<ListItem>> = settingsRepository.getLanguageItems()
        var switchesList: Flowable<out List<ListItem>> = settingsRepository.getSwitchItems()


        return Flowable.merge(titlesList,languagesList,switchesList)
    }

    fun loadSettings() {
        settingsRepository.insertListItem(TitleItem(0, "Language", R.drawable.ic_worlwide, false,
                -1)).subscribe()
        settingsRepository.insertListItem(LanguageItem(1, Languages.English.name, false, true)).subscribe()
        settingsRepository.insertListItem(LanguageItem(2, Languages.Русский.name, false, true)).subscribe()
        settingsRepository.insertListItem(LanguageItem(3, Languages.Беларуская.name, false, true)).subscribe()

        settingsRepository.insertListItem(TitleItem(4, "Notification", R.drawable
                .ic_notification, false, -1)).subscribe()
        settingsRepository.insertListItem(SwitchItem(5, Notifications.Notificaitons.name, true,
                false)).subscribe()
        settingsRepository.insertListItem(SwitchItem(6, Notifications.Sound.name, true, false))
                .subscribe()
    }
}