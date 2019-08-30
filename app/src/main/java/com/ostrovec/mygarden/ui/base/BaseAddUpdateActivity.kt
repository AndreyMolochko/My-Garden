package com.ostrovec.mygarden.ui.base

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
import com.ostrovec.mygarden.ui.addplant.AddPlantHandler
import com.ostrovec.mygarden.ui.addplant.AddPlantViewModel
import com.ostrovec.mygarden.ui.addplant.DialogCameraHandler
import com.ostrovec.mygarden.ui.addplant.DialogNumberPickerHandler
import com.ostrovec.mygarden.ui.updateplant.UpdatePlantViewModel
import com.ostrovec.mygarden.utils.CalendarWorker

abstract class BaseAddUpdateActivity : BaseNavigationActivity() {

    protected val addPlantHandler: AddPlantHandler = object : AddPlantHandler {
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            plant.name = p0.toString()
            checkSaveButton()
        }

        override fun clickOnWatering() {
            showPickerNumberDayDialog(dialogNumberPickerHandler, plant, this@BaseAddUpdateActivity)
        }

        override fun clickOnPhoto() {
            showCameraDialog(dialogCameraHandler, this@BaseAddUpdateActivity)
        }

        override fun clickOnSave() {
            if (plantViewModel is AddPlantViewModel) {
                (plantViewModel as AddPlantViewModel).addPlant(plant)
            } else if (plantViewModel is UpdatePlantViewModel) {
                (plantViewModel as UpdatePlantViewModel).updatePlant(plant).subscribe {
                    Log.e("ondata","plant was updated")
                }
            }
        }
    }

    private val dialogNumberPickerHandler: DialogNumberPickerHandler = object :
            DialogNumberPickerHandler {

        override fun clickOk() {
            closeNumberPickerDialog()
        }

        override fun onNumberPickerValueChange(newValue: Int) {
            plant.setIrrigationPeriod = CalendarWorker.convertDaysToMilliseconds(newValue)
            binding.addPlantsWateringEditText.setText("$newValue ${getString(R.string.days)}")
            checkSaveButton()
        }
    }

    private val dialogCameraHandler: DialogCameraHandler = object : DialogCameraHandler {
        override fun clickOnCamera() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (requestCameraAndStoragePermissions()) {
                    takePhotoFromCamera()
                }
            } else {
                takePhotoFromCamera()
            }
        }

        override fun clickOnGallery() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (requestStoragePermissions()) {
                    choosePhotoFromGallery()
                }
            } else {
                choosePhotoFromGallery()
            }
        }

    }


    protected lateinit var binding: ActivityAddPlantBinding
    protected lateinit var plantViewModel: BaseAddUpdateViewModel
    protected lateinit var plant: Plant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_add_plant)

        enableSaveButton(false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_REQUEST_CODE && data != null) {
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, data!!.data)
            plant.setUrlLocalPhoto = plantViewModel.extractImageFilePath(
                    contentResolver, bitmap,
                    applicationContext
            )
            setImageFromResourses(bitmap)

        } else if (requestCode == CAMERA_REQUEST_CODE && data != null) {
            val bitmap = data!!.extras!!.get("data") as Bitmap
            plant.setUrlLocalPhoto = plantViewModel.extractImageFilePath(
                    contentResolver, bitmap,
                    applicationContext
            )
            setImageFromResourses(bitmap)
        }
        closeCameraDialog()
        checkSaveButton()
    }

    protected fun initSubscribers() {
        plantViewModel.saveButtonClickable.subscribe({
            Log.e("ONDATA", "saveButton" + it)
            enableSaveButton(it)
        })
    }

    protected fun checkSaveButton() {
        plantViewModel.checkSaveButton(plant.name, plant.irrigationPeriod.toString(), plant
                .setUrlLocalPhoto)
    }

    protected fun setImageFromResourses(bitmap: Bitmap) {
        binding.addPlantsPhotoImageView.setImageBitmap(bitmap)
        binding.addPlantsPhotoImageView.visibility = View.VISIBLE
        binding.addPlantsPhotoEditText.visibility = View.GONE
    }

    private fun enableSaveButton(enable: Boolean) {
        binding.addPlantsSaveTextView.isEnabled = enable
    }
}
