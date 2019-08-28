package com.ostrovec.mygarden.ui.updateplant

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.View
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivityAddPlantBinding
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.addplant.AddPlantHandler
import com.ostrovec.mygarden.ui.base.BaseAddUpdateActivity
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity
import java.io.File

class UpdatePlantActivity : BaseAddUpdateActivity() {

    companion object {
        val EXTRA_PLANT_KEY = "extra_plant_key"

        fun open(context: Context, plant: Plant) {
            val intent = Intent(context, UpdatePlantActivity::class.java)
            intent.putExtra(EXTRA_PLANT_KEY, plant)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_add_plant)
        plantViewModel = getViewModel(UpdatePlantViewModel::class.java)
        plant = intent.getParcelableExtra(EXTRA_PLANT_KEY)
        binding.model = plant
        binding.handler = addPlantHandler
        initViews()
        initSubscribers()
    }

    private fun initViews() {
        binding.addPlantsTitleTextView.text = getString(R.string.change_plant_title)
        binding.addPlantsSaveTextView.text = getString(R.string.change_plant_title)
        if (plant.urlLocalPhoto.isNotEmpty()) {
            val bitmap = getBitmapImage(plant.urlLocalPhoto)
            if (bitmap != null) {
                setImageFromResourses(bitmap)
            }
        }
    }
}
