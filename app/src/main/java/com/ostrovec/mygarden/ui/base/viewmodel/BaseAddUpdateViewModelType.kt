package com.ostrovec.mygarden.ui.base.viewmodel

import io.reactivex.subjects.Subject

interface BaseAddUpdateViewModelType : BaseViewModelType{
    val saveButtonClickable : Subject<Boolean>
    fun checkSaveButton(name: String, irrigation: String, photo: String)
}