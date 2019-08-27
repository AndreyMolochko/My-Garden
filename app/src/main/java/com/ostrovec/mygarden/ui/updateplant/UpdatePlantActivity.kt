package com.ostrovec.mygarden.ui.updateplant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivityAddPlantBinding
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.addplant.AddPlantHandler
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity

class UpdatePlantActivity : BaseNavigationActivity() {

    companion object {
        val EXTRA_PLANT_KEY = "extra_plant_key"

        fun open(context: Context, plant: Plant) {
            val intent = Intent(context, UpdatePlantActivity::class.java)
            intent.putExtra(EXTRA_PLANT_KEY,plant)
            context.startActivity(intent)
        }
    }

    val updatePlantHandler: AddPlantHandler = object : AddPlantHandler {
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun clickOnWatering() {

        }

        override fun clickOnPhoto() {

        }

        override fun clickOnSave() {

        }
    }

    private lateinit var binding: ActivityAddPlantBinding
    private lateinit var plant: Plant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_add_plant)
        plant = intent.getParcelableExtra(EXTRA_PLANT_KEY)
        binding.handler = updatePlantHandler
        binding.model = plant
        initViews()
    }

    private fun initViews(){
        binding.addPlantsTitleTextView.text = "Change plant"
    }
}
