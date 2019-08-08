package com.ostrovec.mygarden.ui.base

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.AlertDialogNumberPickerBinding
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.addplant.DialogNumberPickerHandler
import com.ostrovec.mygarden.utils.CalendarWorker
import dagger.android.AndroidInjection
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    protected val CAMERA_REQUEST_CODE = 0
    protected val GALLERY_REQUEST_CODE = 1
    private val minValuePicker = 1
    private val maxValuePicker = 180
    private val defaultValuePicker = 5

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var alertNumberPickerDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        Log.e("ONDATA", "onRequestPermissionsResult")
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePhotoFromCamera()
                } else {
                    Log.e("SNACK", "You need give permission for camera in settings")
                }
            }
        }
    }

    protected fun showAlertPickerNumberDay(handler: DialogNumberPickerHandler, plant: Plant, context: Context) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val alertBinding: AlertDialogNumberPickerBinding = DataBindingUtil.inflate(LayoutInflater
                .from(context), R.layout.alert_dialog_number_picker, null, false)
        alertBinding.handler = handler
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

    protected fun closeAlertDialog() {
        alertNumberPickerDialog.dismiss()
    }

    protected fun <T : ViewModel> getViewModel(cls: Class<T>): T {
        return ViewModelProviders.of(this, viewModelFactory).get(cls)
    }

    protected fun choosePhotoFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    protected fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    protected fun requestCameraPermissions(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
                false
            } else {
                true
            }
        }
        return false
    }
}