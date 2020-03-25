package com.ostrovec.mygarden.ui.base.activity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivityAddPlantBinding
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.addplant.handler.AddPlantHandler
import com.ostrovec.mygarden.ui.addplant.handler.DialogCameraHandler
import com.ostrovec.mygarden.ui.addplant.handler.DialogNumberPickerHandler
import com.ostrovec.mygarden.ui.addplant.viewmodel.AddPlantViewModel
import com.ostrovec.mygarden.ui.base.CustomBindingAdapter
import com.ostrovec.mygarden.ui.base.viewmodel.BaseAddUpdateViewModelType
import com.ostrovec.mygarden.ui.updateplant.viewmodel.UpdatePlantViewModel
import com.ostrovec.mygarden.utils.CalendarWorker

abstract class BaseAddUpdateActivity : BaseNavigationActivity() {

    protected val addPlantHandler: AddPlantHandler = object :
        AddPlantHandler {
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
                (plantViewModel as AddPlantViewModel).addPlant(plant).subscribe {
                    plant.id = it
                    (plantViewModel as AddPlantViewModel).addRemotePlant(plant).subscribe {
                        finish()
                    }
                }
            } else if (plantViewModel is UpdatePlantViewModel) {
                (plantViewModel as UpdatePlantViewModel).updatePlant(plant)
                    .mergeWith((plantViewModel as UpdatePlantViewModel).updateRemotePlant(plant)).subscribe {
                    finish()
                }
            }
        }
    }

    private val dialogNumberPickerHandler: DialogNumberPickerHandler = object :
        DialogNumberPickerHandler {

        override fun clickOk() {
            if (plant.irrigationPeriod == 0L) {
                plant.setIrrigationPeriod = CalendarWorker.convertDaysToMilliseconds(defaultValuePicker)
            }
            closeNumberPickerDialog()
        }

        override fun onNumberPickerValueChange(newValue: Int) {
            plant.setIrrigationPeriod = CalendarWorker.convertDaysToMilliseconds(newValue)
            binding.addPlantsWateringEditText.setText("$newValue ${getString(R.string.days)}")
            checkSaveButton()
        }
    }

    private val dialogCameraHandler: DialogCameraHandler = object :
        DialogCameraHandler {
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
    protected lateinit var plantViewModel: BaseAddUpdateViewModelType
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
            plant.setUrlLocalPhoto = CustomBindingAdapter.encodeBitmap(bitmap)
            setImageFromResourses(plant.setUrlLocalPhoto)

        } else if (requestCode == CAMERA_REQUEST_CODE && data != null) {
            val bitmap = data!!.extras!!.get("data") as Bitmap
            plant.setUrlLocalPhoto = CustomBindingAdapter.encodeBitmap(bitmap)
            setImageFromResourses(plant.setUrlLocalPhoto)
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

    protected fun setImageFromResourses(urlPhoto: String) {
        Glide.with(this).asBitmap().load(CustomBindingAdapter.decodeBitmap(urlPhoto)).into(binding.addPlantsPhotoImageView)
        binding.addPlantsPhotoImageView.visibility = View.VISIBLE
        binding.addPlantsPhotoEditText.visibility = View.GONE
    }

    private fun enableSaveButton(enable: Boolean) {
        binding.addPlantsSaveTextView.isEnabled = enable
    }
}
