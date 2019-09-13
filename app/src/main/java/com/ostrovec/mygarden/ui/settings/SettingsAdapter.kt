package com.ostrovec.mygarden.ui.settings

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ostrovec.mygarden.databinding.ItemRecyclerSettingsLanguageBinding
import com.ostrovec.mygarden.databinding.ItemRecyclerSettingsSwitchBinding
import com.ostrovec.mygarden.databinding.ItemRecyclerSettingsTitleBinding
import com.ostrovec.mygarden.ui.settings.model.LanguageItem
import com.ostrovec.mygarden.ui.settings.model.ListItem
import com.ostrovec.mygarden.ui.settings.model.SwitchItem
import com.ostrovec.mygarden.ui.settings.model.TitleItem

class SettingsAdapter(var settingsList: List<ListItem>) : RecyclerView
.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER_ITEM = 0
    private val TYPE_LANGUAGE_ITEM = 1
    private val TYPE_SWITCH_ITEM = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return settingsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemViewType(position: Int): Int {
        return when (settingsList[position]) {
            is TitleItem -> TYPE_HEADER_ITEM
            is LanguageItem -> TYPE_LANGUAGE_ITEM
            else -> TYPE_SWITCH_ITEM
        }
    }

    inner class HeaderItemViewHolder(private var binding: ItemRecyclerSettingsTitleBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(titleItem: TitleItem) {
            binding.model = titleItem
        }
    }

    inner class LanguageItemViewHolder(private var binding:ItemRecyclerSettingsLanguageBinding):
            RecyclerView.ViewHolder(binding.root){
        fun bind(languageItem:LanguageItem){
            binding.model = languageItem
        }
    }

    inner class SwitchItemViewHolder(private var binding: ItemRecyclerSettingsSwitchBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun bing(switchItem: SwitchItem){
            binding.model = switchItem
        }
    }


}