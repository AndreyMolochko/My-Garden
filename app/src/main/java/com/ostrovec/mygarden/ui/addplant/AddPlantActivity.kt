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

    val addPLantHandler: AddPlantHandler = object : AddPlantHandler {
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            plant.name = p0.toString()
            checkSaveButton()
        }

        override fun clickOnWatering() {
            showPickerNumberDayDialog(dialogNumberPickerHandler, plant, this@AddPlantActivity)
        }

        override fun clickOnPhoto() {
            showCameraDialog(dialogCameraHandler, this@AddPlantActivity)
        }

        override fun clickOnSave() {
            (plantViewModel as AddPlantViewModel).addPlant(plant)
        }
    }

    val dialogNumberPickerHandler: DialogNumberPickerHandler = object : DialogNumberPickerHandler {

        override fun clickOk() {
            closeNumberPickerDialog()
        }

        override fun onNumberPickerValueChange(newValue: Int) {
            plant.setIrrigationPeriod = CalendarWorker.convertDaysToMilliseconds(newValue)
            binding.addPlantsWateringEditText.setText("$newValue ${getString(R.string.days)}")
            checkSaveButton()
        }
    }

    val dialogCameraHandler: DialogCameraHandler = object : DialogCameraHandler {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_add_plant)
        plantViewModel = getViewModel(AddPlantViewModel::class.java)
        binding.handler = addPLantHandler
        binding.model = plant
        enableSaveButton(false)

        initSubscribers()
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

    private fun initSubscribers() {
        plantViewModel.saveButtonClickable.subscribe({
            Log.e("ONDATA", "saveButton" + it)
            enableSaveButton(it)
        })
    }
}
