package com.ostrovec.mygarden.ui.base.activity

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.AlertDialogCameraBinding
import com.ostrovec.mygarden.databinding.AlertDialogNumberPickerBinding
import com.ostrovec.mygarden.room.model.Plant
import com.ostrovec.mygarden.ui.addplant.handler.DialogCameraHandler
import com.ostrovec.mygarden.ui.addplant.handler.DialogNumberPickerHandler
import com.ostrovec.mygarden.utils.CalendarWorker
import dagger.android.AndroidInjection
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    protected val CAMERA_REQUEST_CODE = 0
    protected val GALLERY_REQUEST_CODE = 1
    private val CAMERA_AND_STORADE_REQUEST_CODE = 2
    private val WRITE_STORAGE_REQUEST_CODE = 3
    private val minValuePicker = 1
    private val maxValuePicker = 180
    private val defaultValuePicker = 5
    private val permissions = arrayOf(Manifest.permission.CAMERA, Manifest
            .permission.WRITE_EXTERNAL_STORAGE)

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var alertNumberPickerDialog: AlertDialog
    private lateinit var alertCameraDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            CAMERA_AND_STORADE_REQUEST_CODE -> {
                if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    takePhotoFromCamera()
                } else {
                    showSnackbar(getRootView(), getString(R.string.enable_permissions_settings_appliction))
                }
            }
            WRITE_STORAGE_REQUEST_CODE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    choosePhotoFromGallery()
                } else {
                    showSnackbar(getRootView(), getString(R.string.enable_permissions_settings_appliction))
                }
            }
        }
    }

    protected fun showPickerNumberDayDialog(handler: DialogNumberPickerHandler, plant: Plant, context: Context) {
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

    protected fun showCameraDialog(handler: DialogCameraHandler, context: Context) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val alertCameraBinding: AlertDialogCameraBinding = DataBindingUtil.inflate(LayoutInflater
                .from(context), R.layout.alert_dialog_camera, null, false)
        builder.setView(alertCameraBinding.root)
        alertCameraBinding.handler = handler
        alertCameraDialog = builder.create()
        alertCameraDialog?.show()
    }

    protected fun closeNumberPickerDialog() {
        alertNumberPickerDialog.dismiss()
    }

    protected fun closeCameraDialog() {
        alertCameraDialog.dismiss()
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

    protected fun requestCameraAndStoragePermissions(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return if (!permissions.all {
                        checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED
                    }) {
                requestPermissions(permissions,
                        CAMERA_AND_STORADE_REQUEST_CODE)
                false
            } else {
                true
            }
        }
        return false
    }

    protected fun requestStoragePermissions(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager
                            .PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission
                        .WRITE_EXTERNAL_STORAGE),
                        WRITE_STORAGE_REQUEST_CODE)
                false
            } else {
                true
            }
        }
        return false
    }

    protected fun showSnackbar(view: View, text: String) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun getRootView(): View {
        return window.decorView.findViewById(android.R.id.content)
    }

}