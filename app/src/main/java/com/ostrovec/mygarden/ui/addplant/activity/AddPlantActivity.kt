package com.ostrovec.mygarden.ui.addplant.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.addplant.viewmodel.AddPlantViewModel
import com.ostrovec.mygarden.ui.base.activity.BaseAddUpdateActivity

class AddPlantActivity : BaseAddUpdateActivity() {

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, AddPlantActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        plant = Plant(0, "", 0, "", "server", 0, 0)
        binding.model = plant
        binding.handler = addPlantHandler
        plantViewModel = getViewModel(AddPlantViewModel::class.java)
        initSubscribers()
    }
}
