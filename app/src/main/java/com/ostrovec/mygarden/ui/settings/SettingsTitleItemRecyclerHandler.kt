package com.ostrovec.mygarden.ui.settings

import com.ostrovec.mygarden.ui.settings.model.TitleItem

interface SettingsTitleItemRecyclerHandler {
    fun onClickItem(titleItem: TitleItem)
}