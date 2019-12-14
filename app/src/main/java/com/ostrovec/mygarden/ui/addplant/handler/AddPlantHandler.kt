package com.ostrovec.mygarden.ui.addplant.handler

interface AddPlantHandler {
    fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)

    fun clickOnWatering()

    fun clickOnPhoto()

    fun clickOnSave()
}