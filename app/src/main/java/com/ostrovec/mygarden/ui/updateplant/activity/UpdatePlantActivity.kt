package com.ostrovec.mygarden.ui.updateplant.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.base.activity.BaseAddUpdateActivity
import com.ostrovec.mygarden.ui.updateplant.viewmodel.UpdatePlantViewModel

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
            setImageFromResourses(plant.urlLocalPhoto)
        }
    }
}
