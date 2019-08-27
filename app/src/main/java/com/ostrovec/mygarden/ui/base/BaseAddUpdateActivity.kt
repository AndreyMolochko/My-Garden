package com.ostrovec.mygarden.ui.base

import android.graphics.Bitmap
import android.view.View
import com.ostrovec.mygarden.databinding.ActivityAddPlantBinding
import com.ostrovec.mygarden.room.model.Plant

abstract class BaseAddUpdateActivity : BaseNavigationActivity() {


    protected lateinit var binding: ActivityAddPlantBinding
    protected lateinit var plantViewModel: BaseAddUpdateViewModel
    protected val plant: Plant = Plant(0, "", 0, "", "server", 0, 0)


    protected fun setImageFromResourses(bitmap: Bitmap) {
        binding.addPlantsPhotoImageView.setImageBitmap(bitmap)
        binding.addPlantsPhotoImageView.visibility = View.VISIBLE
        binding.addPlantsPhotoEditText.visibility = View.GONE
    }

    protected fun enableSaveButton(enable: Boolean) {
        binding.addPlantsSaveTextView.isEnabled = enable
    }

    protected fun checkSaveButton() {
        plantViewModel.checkSaveButton(plant.name, plant.irrigationPeriod.toString(), plant
                .setUrlLocalPhoto)
    }
}
