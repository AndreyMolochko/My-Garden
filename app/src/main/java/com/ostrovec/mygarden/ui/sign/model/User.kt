package com.ostrovec.mygarden.ui.sign.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

data class User(var name: String,
           var email: String,
           var password: String): BaseObservable() {

    var setName: String
    @Bindable
    get()=name
    set(value){
        name = value
        notifyPropertyChanged(BR.setName)
    }

    var setEmail:String
    @Bindable
    get()=email
    set(value){
        email = value
        notifyPropertyChanged(BR.setEmail)
    }

    var setPassword:String
    @Bindable
    get()=password
    set(value){
        password = value
        notifyPropertyChanged(BR.setPassword)
    }
}