package com.ostrovec.mygarden.ui.addplant

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivityAddPlantBinding
import com.ostrovec.mygarden.databinding.AlertDialogNumberPickerBinding
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity
import javax.inject.Inject

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
    }

    val dialogNumberPickerHandler: DialogNumberPickerHandler = object : DialogNumberPickerHandler {

        override fun clickOk() {
            closeAlertDialog()
        }

        override fun onNumberPickerValueChange(newValue: Int) {
            //TODO two way binding or adapter
            binding.addPlantsWateringEditText.setText("$newValue ${getString(R.string.days)}")
        }
    }

    private lateinit var binding: ActivityAddPlantBinding
    private lateinit var addPlantViewModel: AddPlantViewModel
    private lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_add_plant)
        addPlantViewModel = getViewModel(AddPlantViewModel::class.java)
        binding.handler = addPLantHandler
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
        alertDialog = builder.create()
        alertDialog?.show()
    }

    private fun closeAlertDialog() {
        alertDialog.dismiss()
    }
}
