package com.ostrovec.mygarden.ui.addplant

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivityAddPlantBinding
import com.ostrovec.mygarden.databinding.AlertDialogCameraBinding
import com.ostrovec.mygarden.databinding.AlertDialogNumberPickerBinding
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity

class AddPlantActivity : BaseNavigationActivity() {

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, AddPlantActivity::class.java)
            context.startActivity(intent)
        }
    }

    val addPLantHandler: AddPlantHandler = object : AddPlantHandler {
        override fun clickOnWatering() {
            showAlertPickerNumberDay()
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
            //TODO two way binding or adapter
            binding.addPlantsWateringEditText.setText("$newValue ${getString(R.string.days)}")
            chechSaveButton()
        }
    }

    private lateinit var binding: ActivityAddPlantBinding
    private lateinit var addPlantViewModel: AddPlantViewModel
    private lateinit var alertNumberPickerDialog: AlertDialog
    private lateinit var alertCameraDialog: AlertDialog
    private val plant: Plant = Plant(0, "Flower", 100, "", "server", 100, 200)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_add_plant)
        addPlantViewModel = getViewModel(AddPlantViewModel::class.java)
        binding.handler = addPLantHandler
        binding.model = plant

        initSubscribers()
    }

    private fun initSubscribers() {
        addPlantViewModel.saveButtonClickable.subscribe({
            Log.e("ONDATA", "saveButton" + it)
        })
    }

    private fun showAlertPickerNumberDay() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val alertBinding: AlertDialogNumberPickerBinding = DataBindingUtil.inflate(LayoutInflater
                .from(this@AddPlantActivity), R.layout.alert_dialog_number_picker, null, false)
        alertBinding.handler = dialogNumberPickerHandler
        alertBinding.pickerNumberTitleTextView.setText(getString(R.string.period_in_days))
        alertBinding.pickerNumberNumberPicker.minValue = 1
        alertBinding.pickerNumberNumberPicker.maxValue = 180
        alertBinding.pickerNumberNumberPicker.value = 5
        builder.setView(alertBinding.root)
        alertNumberPickerDialog = builder.create()
        alertNumberPickerDialog?.show()
    }

    private fun closeAlertDialog() {
        alertNumberPickerDialog.dismiss()
    }

    private fun showAlertCameraPicker() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val alertCameraBinding: AlertDialogCameraBinding = DataBindingUtil.inflate(LayoutInflater
                .from(this@AddPlantActivity), R.layout.alert_dialog_camera, null, false)
        builder.setView(alertCameraBinding.root)
        alertNumberPickerDialog = builder.create()
        alertCameraDialog = builder.create()
        alertCameraDialog?.show()
    }

    private fun chechSaveButton() {
        addPlantViewModel.checkSaveButton(getName(), getIrrigation(), getPhotos())
    }

    private fun getName(): String {
        return binding.addPlantsNameEditText.text.toString()
    }

    private fun getIrrigation(): String {
        return binding.addPlantsWateringEditText.text.toString()
    }

    private fun getPhotos(): String {
        return binding.addPlantsPhotoEditText.text.toString()
    }
}
