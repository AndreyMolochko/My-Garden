package com.ostrovec.mygarden.room.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ostrovec.mygarden.BR

@Entity(tableName = "SettingsTitle")
data class TitleItem(
        @PrimaryKey
        override var id: Int = -1,
        var title: String = "",
        @ColumnInfo(name = "icon_id")
        var iconId: Int = -1,
        @ColumnInfo(name = "is_dropped_down")
        var isDroppedDown: Boolean = false,
        var position: Int) : ListItem, BaseObservable() {

    var setDroppedDown: Boolean
        @Bindable
        get() = isDroppedDown
        set(value) {
            isDroppedDown = value
            notifyPropertyChanged(BR.setDroppedDown)
        }
}