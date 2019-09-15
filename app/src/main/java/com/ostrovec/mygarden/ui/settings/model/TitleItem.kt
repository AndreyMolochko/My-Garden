package com.ostrovec.mygarden.ui.settings.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ostrovec.mygarden.BR

class TitleItem(var id: Int = -1,
                var title: String = "",
                var iconId: Int = -1,
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