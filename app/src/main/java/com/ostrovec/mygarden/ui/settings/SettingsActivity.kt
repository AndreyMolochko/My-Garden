package com.ostrovec.mygarden.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.common.comparators.ListItemComparatorById
import com.ostrovec.mygarden.databinding.ActivitySettingsBinding
import com.ostrovec.mygarden.room.model.LanguageItem
import com.ostrovec.mygarden.room.model.ListItem
import com.ostrovec.mygarden.room.model.SwitchItem
import com.ostrovec.mygarden.room.model.TitleItem
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity
import com.ostrovec.mygarden.ui.settings.model.*
import java.util.*

class SettingsActivity : BaseNavigationActivity(), SettingsAdapter.SettingsListener {

    companion object {
        val TITLE_LANGUAGES_ITEM = "Languages"
        val TITLE_NOTIFICATION_ITEM = "Notifications"
        fun open(context: Context) {
            context.startActivity(Intent(context, SettingsActivity::class.java))
        }
    }

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var settingsAdapter: SettingsAdapter
    private lateinit var settingsViewModel: SettingsViewModel
    private var settingsList: MutableList<ListItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_settings)
        settingsViewModel = getViewModel(SettingsViewModel::class.java)
        settingsViewModel.getSettings().subscribe {
            Log.e("subscribe", "subscribe")
            settingsList.addAll(it.sortedBy { it.id })
            Collections.sort(settingsList, ListItemComparatorById())
            displayRecyclerView()
        }
    }

    override fun onChangeRadioButton(languageItem: LanguageItem) {

    }

    private fun displayRecyclerView() {
        binding.settingsRecyclerView.layoutManager = LinearLayoutManager(this)
        settingsAdapter = SettingsAdapter(this, settingsList)
        binding.settingsRecyclerView.adapter = settingsAdapter
    }
}
