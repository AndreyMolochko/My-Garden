package com.ostrovec.mygarden.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivitySettingsBinding
import com.ostrovec.mygarden.room.model.LanguageItem
import com.ostrovec.mygarden.room.model.ListItem
import com.ostrovec.mygarden.room.model.SwitchItem
import com.ostrovec.mygarden.room.model.TitleItem
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity
import com.ostrovec.mygarden.ui.settings.model.*

class SettingsActivity : BaseNavigationActivity(),SettingsAdapter.SettingsListener {

    companion object {
        val TITLE_LANGUAGES_ITEM = "Languages"
        val TITLE_NOTIFICATION_ITEM = "Notifications"
        fun open(context: Context) {
            context.startActivity(Intent(context, SettingsActivity::class.java))
        }
    }

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var settingsAdapter: SettingsAdapter
    private var settingsList: MutableList<ListItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_settings)
        initLanguages()
        initNotifications()
        displayRecyclerView()
    }

    override fun onChangeRadioButton(languageItem: LanguageItem) {

    }

    private fun displayRecyclerView() {
        binding.settingsRecyclerView.layoutManager = LinearLayoutManager(this)
        settingsAdapter = SettingsAdapter(this, settingsList)
        binding.settingsRecyclerView.adapter = settingsAdapter
    }

    private fun initLanguages() {
        settingsList.add(TitleItem(0, "Language", R.drawable.ic_worlwide, false, -1))

        for (language in Languages.values()) {
            settingsList.add(LanguageItem(0, language.name, false, true))
        }
    }

    private fun initNotifications() {
        settingsList.add(TitleItem(4, "Notification", R.drawable.ic_notification, false, -1))

        for (notification in Notifications.values()) {
            settingsList.add(SwitchItem(0, notification.name, true, false))
        }
    }
}
