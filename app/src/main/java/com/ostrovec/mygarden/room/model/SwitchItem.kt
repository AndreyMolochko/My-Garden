package com.ostrovec.mygarden.room.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ostrovec.mygarden.BR

class SwitchItem(var id: Int = -1,
                 var text: String = "",
                 var isChecked: Boolean = false,
                 var isVisible: Boolean = false) : ListItem, BaseObservable() {

    var setVisible:Boolean
        @Bindable
        get() = isVisible
        set(value){
            isVisible = value
            notifyPropertyChanged(BR.setVisible)
        }
}