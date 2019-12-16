package com.ostrovec.mygarden.ui.myplants.handlers

import com.ostrovec.mygarden.room.model.Plant

interface DeleteDialogHandler {
    fun onClickYes(plant: Plant)

    fun onClickCancel()
}
