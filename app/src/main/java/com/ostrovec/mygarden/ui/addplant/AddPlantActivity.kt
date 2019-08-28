package com.ostrovec.mygarden.ui.addplant

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivityAddPlantBinding
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.base.BaseAddUpdateActivity
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity
import com.ostrovec.mygarden.utils.CalendarWorker

class AddPlantActivity : BaseAddUpdateActivity() {

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, AddPlantActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        plant = Plant(0, "hghh", 0, "", "server", 0, 0)
        binding.model = plant
        binding.handler = addPlantHandler
        plantViewModel = getViewModel(AddPlantViewModel::class.java)
        initSubscribers()
    }
}
