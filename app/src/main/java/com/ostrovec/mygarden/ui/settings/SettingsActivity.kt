package com.ostrovec.mygarden.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivitySettingsBinding
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity
import com.ostrovec.mygarden.ui.settings.model.LanguageItem
import com.ostrovec.mygarden.ui.settings.model.ListItem
import com.ostrovec.mygarden.ui.settings.model.SwitchItem
import com.ostrovec.mygarden.ui.settings.model.TitleItem

class SettingsActivity : BaseNavigationActivity() {

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

    private fun displayRecyclerView() {
        binding.settingsRecyclerView.layoutManager = LinearLayoutManager(this)
        settingsAdapter = SettingsAdapter(settingsList)
        binding.settingsRecyclerView.adapter = settingsAdapter
    }

    private fun initLanguages() {
        settingsList.add(TitleItem(0, "Language", R.drawable.ic_worlwide))

        for (language in Languages.values()) {
            settingsList.add(LanguageItem(0, language.name))
        }
    }

    private fun initNotifications() {
        settingsList.add(TitleItem(4, "Notification", R.drawable.ic_worlwide))

        for (notification in Notifications.values()) {
            settingsList.add(SwitchItem(0, notification.name, true))
        }
    }
}
