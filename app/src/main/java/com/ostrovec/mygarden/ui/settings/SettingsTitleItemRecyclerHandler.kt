package com.ostrovec.mygarden.ui.settings

import com.ostrovec.mygarden.room.model.TitleItem

interface SettingsTitleItemRecyclerHandler {
    fun onClickItem(titleItem: TitleItem)
}