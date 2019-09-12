package com.ostrovec.mygarden.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivitySettingsBinding
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity

class SettingsActivity : BaseNavigationActivity() {

    companion object {
        val TITLE_LANGUAGES_ITEM = "Languages"
        val TITLE_NOTIFICATION_ITEM = "Notifications"
        fun open(context: Context) {
            context.startActivity(Intent(context, SettingsActivity::class.java))
        }
    }

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var settingsData: HashMap<String, Set<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_settings)
        initLanguages()
        initNotifications()
    }

    private fun initLanguages() {
        var languagesSet = mutableSetOf<String>()

        for (language in Languages.values()) {
            languagesSet.add(language.name)
        }
        settingsData[TITLE_LANGUAGES_ITEM] = languagesSet
    }

    private fun initNotifications() {
        var notificationsSet = mutableSetOf<String>()

        for (notification in Notifications.values()) {
            notificationsSet.add(notification.name)
        }
        settingsData[TITLE_NOTIFICATION_ITEM] = notificationsSet
    }
}
