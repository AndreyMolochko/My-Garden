package com.ostrovec.mygarden.ui.myplants

import com.ostrovec.mygarden.room.model.Plant

interface MyPlantsItemRecyclerHandler {
    fun clickOnUpdate(plant: Plant)

    fun clickOnGarbage()
}