package com.ostrovec.mygarden.ui.addplant

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivityAddPlantBinding
import com.ostrovec.mygarden.databinding.AlertDialogCameraBinding
import com.ostrovec.mygarden.databinding.AlertDialogNumberPickerBinding
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity
import com.ostrovec.mygarden.utils.CalendarWorker

class AddPlantActivity : BaseNavigationActivity() {

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, AddPlantActivity::class.java)
            context.startActivity(intent)
        }
    }

    val addPLantHandler: AddPlantHandler = object : AddPlantHandler {
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            checkSaveButton()
        }

        override fun clickOnWatering() {
            showAlertPickerNumberDay(dialogNumberPickerHandler, plant, this@AddPlantActivity)
        }

        override fun clickOnPhoto() {
            showAlertCameraPicker()
        }

        override fun clickOnSave() {
            addPlantViewModel.addPlant(plant)
        }
    }

    val dialogNumberPickerHandler: DialogNumberPickerHandler = object : DialogNumberPickerHandler {

        override fun clickOk() {
            closeAlertDialog()
        }

        override fun onNumberPickerValueChange(newValue: Int) {
            plant.setIrrigationPeriod = CalendarWorker.convertDaysToMilliseconds(newValue)
            binding.addPlantsWateringEditText.setText("$newValue ${getString(R.string.days)}")
            checkSaveButton()
        }
    }

    val dialogCameraHandler: DialogCameraHandler = object : DialogCameraHandler {
        override fun clickOnCamera() {
            if (requestCameraPermissions()) {
                takePhotoFromCamera()
            }
        }

        override fun clickOnGallery() {
            choosePhotoFromGallery()
        }

    }

    private lateinit var binding: ActivityAddPlantBinding
    private lateinit var addPlantViewModel: AddPlantViewModel
    //private lateinit var alertNumberPickerDialog: AlertDialog
    private lateinit var alertCameraDialog: AlertDialog
    private val plant: Plant = Plant(0, "", 0, "", "server", 0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_add_plant)
        addPlantViewModel = getViewModel(AddPlantViewModel::class.java)
        binding.handler = addPLantHandler
        binding.model = plant
        enableSaveButton(false)

        initSubscribers()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_REQUEST_CODE && data != null) {
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, data!!.data)
            plant.setUrlLocalPhoto = addPlantViewModel.extractImageFilePath(contentResolver, bitmap,
                    applicationContext)
            setImageFromResourses(bitmap)

        } else if (requestCode == CAMERA_REQUEST_CODE && data != null) {
            val bitmap = data!!.extras!!.get("data") as Bitmap
            plant.setUrlLocalPhoto = addPlantViewModel.extractImageFilePath(contentResolver, bitmap,
                    applicationContext)
            setImageFromResourses(bitmap)
        }
        checkSaveButton()
    }

    private fun initSubscribers() {
        addPlantViewModel.saveButtonClickable.subscribe({
            Log.e("ONDATA", "saveButton" + it)
            enableSaveButton(it)
        })
    }

    /*private fun showAlertPickerNumberDay() {//TODO refactoring this method
        val minValuePicker = 1
        val maxValuePicker = 180
        val defaultValuePicker = 5
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val alertBinding: AlertDialogNumberPickerBinding = DataBindingUtil.inflate(LayoutInflater
                .from(this@AddPlantActivity), R.layout.alert_dialog_number_picker, null, false)
        alertBinding.handler = dialogNumberPickerHandler
        alertBinding.pickerNumberTitleTextView.setText(getString(R.string.period_in_days))
        alertBinding.pickerNumberNumberPicker.minValue = minValuePicker
        alertBinding.pickerNumberNumberPicker.maxValue = maxValuePicker
        if (plant.irrigationPeriod > 0) {
            alertBinding.pickerNumberNumberPicker.value = CalendarWorker
                    .convertMillisecondsInDays(plant.irrigationPeriod)
        } else {
            alertBinding.pickerNumberNumberPicker.value = defaultValuePicker
        }
        builder.setView(alertBinding.root)
        alertNumberPickerDialog = builder.create()
        alertNumberPickerDialog?.show()
    }

    private fun closeAlertDialog() {
        alertNumberPickerDialog.dismiss()
    }*/

    private fun showAlertCameraPicker() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val alertCameraBinding: AlertDialogCameraBinding = DataBindingUtil.inflate(LayoutInflater
                .from(this@AddPlantActivity), R.layout.alert_dialog_camera, null, false)
        builder.setView(alertCameraBinding.root)
        alertCameraBinding.handler = dialogCameraHandler
        alertCameraDialog = builder.create()
        alertCameraDialog?.show()
    }

    private fun setImageFromResourses(bitmap: Bitmap) {
        binding.addPlantsPhotoImageView.setImageBitmap(bitmap)
        binding.addPlantsPhotoImageView.visibility = View.VISIBLE
        binding.addPlantsPhotoEditText.visibility = View.GONE
    }

    private fun enableSaveButton(enable: Boolean) {
        binding.addPlantsSaveTextView.isEnabled = enable
    }

    private fun checkSaveButton() {
        addPlantViewModel.checkSaveButton(plant.name, plant.irrigationPeriod.toString(), plant
                .setUrlLocalPhoto)
    }
}
