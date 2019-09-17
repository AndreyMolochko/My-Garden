package com.ostrovec.mygarden.room.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ostrovec.mygarden.BR

@Entity(tableName = "SettingsSwitch")
data class SwitchItem(
        @PrimaryKey
        var id: Int = -1,
        var text: String = "",
        @ColumnInfo(name = "is_checked")
        var isChecked: Boolean = false,
        @ColumnInfo(name = "is_visible")
        var isVisible: Boolean = false) : ListItem, BaseObservable() {

    var setVisible: Boolean
        @Bindable
        get() = isVisible
        set(value) {
            isVisible = value
            notifyPropertyChanged(BR.setVisible)
        }
}