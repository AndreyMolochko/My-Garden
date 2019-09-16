package com.ostrovec.mygarden.ui.settings.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.ostrovec.mygarden.BR

class LanguageItem(var id: Int = -1,
                   var text: String = "",
                   var isVisible: Boolean = false,
                   var isCurrentLanguage: Boolean = false) : ListItem, BaseObservable() {

    var setVisible: Boolean
        @Bindable
        get() = isVisible
        set(value) {
            isVisible = value
            notifyPropertyChanged(BR.setVisible)
        }

    var setCurrentLanguage: Boolean
        @Bindable
        get() = isCurrentLanguage
        set(value) {
            isCurrentLanguage = value
            notifyPropertyChanged(BR.setCurrentLanguage)
        }

}