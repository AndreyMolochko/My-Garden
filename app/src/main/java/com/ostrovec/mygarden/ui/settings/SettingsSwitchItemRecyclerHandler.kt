package com.ostrovec.mygarden.ui.settings

import com.ostrovec.mygarden.room.model.SwitchItem

interface SettingsSwitchItemRecyclerHandler {
    fun onChangeSwitchItem(switchItem: SwitchItem)
}