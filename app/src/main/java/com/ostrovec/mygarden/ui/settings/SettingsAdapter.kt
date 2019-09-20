package com.ostrovec.mygarden.ui.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ItemRecyclerSettingsLanguageBinding
import com.ostrovec.mygarden.databinding.ItemRecyclerSettingsSwitchBinding
import com.ostrovec.mygarden.databinding.ItemRecyclerSettingsTitleBinding
import com.ostrovec.mygarden.room.model.LanguageItem
import com.ostrovec.mygarden.room.model.ListItem
import com.ostrovec.mygarden.room.model.SwitchItem
import com.ostrovec.mygarden.room.model.TitleItem
import com.ostrovec.mygarden.ui.settings.model.Notifications

class SettingsAdapter(var callback: SettingsListener, var settingsList: List<ListItem>) :
        RecyclerView
        .Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER_ITEM = 0
    private val TYPE_LANGUAGE_ITEM = 1
    private val TYPE_SWITCH_ITEM = 2

    interface SettingsListener {
        fun onChangeRadioButton(oldLanguage: LanguageItem, newLanguage: LanguageItem)

        fun onChangeSwitch(notificationSwitch: SwitchItem, soundSwitch: SwitchItem)
    }

    val settingsTitleItemRecyclerHandler: SettingsTitleItemRecyclerHandler = object :
            SettingsTitleItemRecyclerHandler {
        override fun onClickItem(titleItem: TitleItem) {
            titleItem.setDroppedDown = !titleItem.setDroppedDown
            dropDownItems(titleItem.position)
        }

    }

    val settingsLanguageItemRecyclerHandler: SettingsLanguageItemRecyclerHandler = object :
            SettingsLanguageItemRecyclerHandler {
        override fun onClickLanguageItem(languageItem: LanguageItem) {

            val oldLanguage = removeCurrentLanguage()
            languageItem.setCurrentLanguage = true
            callback.onChangeRadioButton(oldLanguage, languageItem)
            oldLanguage.isVisible = true
            languageItem.isVisible = true
            notifyDataSetChanged()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER_ITEM -> {
                val binding: ItemRecyclerSettingsTitleBinding = DataBindingUtil.inflate(
                        inflater, R.layout.item_recycler_settings_title, parent, false
                )
                HeaderItemViewHolder(binding)
            }
            TYPE_LANGUAGE_ITEM -> {
                val binding: ItemRecyclerSettingsLanguageBinding = DataBindingUtil.inflate(
                        inflater, R.layout.item_recycler_settings_language, parent, false
                )
                LanguageItemViewHolder(binding)
            }
            else -> {
                val binding: ItemRecyclerSettingsSwitchBinding = DataBindingUtil.inflate(
                        inflater, R.layout.item_recycler_settings_switch, parent, false
                )
                SwitchItemViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return settingsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (settingsList[position]) {
            is TitleItem -> {
                val viewHolder = holder as HeaderItemViewHolder
                viewHolder.bind(settingsList[position] as TitleItem)
            }
            is LanguageItem -> {
                val viewHolder = holder as LanguageItemViewHolder
                viewHolder.bind(settingsList[position] as LanguageItem)
                viewHolder.binding.itemRecyclerLanguageRadioButton.isChecked =
                        (settingsList[position] as LanguageItem).isCurrentLanguage
            }
            else -> {
                val viewHolder = holder as SwitchItemViewHolder
                viewHolder.bind(settingsList[position] as SwitchItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (settingsList[position]) {
            is TitleItem -> TYPE_HEADER_ITEM
            is LanguageItem -> TYPE_LANGUAGE_ITEM
            else -> TYPE_SWITCH_ITEM
        }
    }

    private fun dropDownItems(position: Int) {
        var counter: Int = position

        if (settingsList.get(counter) is TitleItem) {
            counter++
            while (counter < settingsList.size) {
                if (settingsList.get(counter) is TitleItem) {
                    break
                }
                if (settingsList.get(counter) is LanguageItem) {
                    (settingsList.get(counter) as LanguageItem).setVisible = !(settingsList
                            .get(counter) as LanguageItem).setVisible
                } else if (settingsList.get(counter) is SwitchItem) {
                    (settingsList.get(counter) as SwitchItem).setVisible = !(settingsList.get(counter) as SwitchItem).setVisible
                }
                counter++
            }
        }
    }

    private fun removeCurrentLanguage(): LanguageItem {
        for (language in settingsList) {
            if (language is LanguageItem) {
                if (language.isCurrentLanguage) {
                    language.isCurrentLanguage = false

                    return language
                }
            }
        }

        return LanguageItem(1, "error", true, false)
    }

    private fun getNotificationSwitch():SwitchItem{
        for(switch in settingsList){
            if(switch is SwitchItem){
                if(switch.text == Notifications.Notificaitons.name){
                    return switch
                }
            }
        }

        return SwitchItem(1,"error",false,false)
    }

    private fun getSoundSwitch(): SwitchItem{
        for(switch in settingsList){
            if(switch is SwitchItem){
                if(switch.text == Notifications.Sound.name){
                    return switch
                }
            }
        }

        return SwitchItem(1,"error",false,false)
    }

    inner class HeaderItemViewHolder(private var binding: ItemRecyclerSettingsTitleBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(titleItem: TitleItem) {
            titleItem.position = layoutPosition
            binding.model = titleItem
            binding.handler = settingsTitleItemRecyclerHandler
        }
    }

    inner class LanguageItemViewHolder(var binding: ItemRecyclerSettingsLanguageBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(languageItem: LanguageItem) {
            binding.model = languageItem
            binding.handler = settingsLanguageItemRecyclerHandler
        }
    }

    inner class SwitchItemViewHolder(private var binding: ItemRecyclerSettingsSwitchBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(switchItem: SwitchItem) {
            binding.model = switchItem
            setSwitchLogic(switchItem)
            binding.itemRecyclerSwitchSwitch.setOnCheckedChangeListener {
                buttonView, isChecked ->
                switchItem.isChecked = isChecked
                setSwitchLogic(switchItem)
                callback.onChangeSwitch(getNotificationSwitch(),getSoundSwitch())
                notifyDataSetChanged()
            }
        }

        fun setSwitchLogic(switchItem: SwitchItem){
            if(switchItem.text == Notifications.Sound.name) {
                if (getNotificationSwitch().isChecked) {
                    binding.itemRecyclerSwitchSwitch.isEnabled = true
                }else{
                    binding.itemRecyclerSwitchSwitch.isChecked = false
                    binding.itemRecyclerSwitchSwitch.isEnabled = false
                }
            }
        }
    }


}