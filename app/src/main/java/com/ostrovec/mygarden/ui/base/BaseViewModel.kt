package com.ostrovec.mygarden.ui.base

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import java.io.ByteArrayOutputStream
import java.io.File

abstract class BaseViewModel : ViewModel() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun extractImageFilePath(contentResolver: ContentResolver, bitmap: Bitmap, context: Context): String {
        val tempUri: Uri = getImageUri(context, bitmap)
        val file = File(getRealPathFromURI(contentResolver, tempUri))

        return file.path
    }

    private fun getImageUri(context: Context, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null)

        return Uri.parse(path)
    }

    private fun getRealPathFromURI(contentResolver: ContentResolver?, uri: Uri): String {
        var path = ""
        if (contentResolver != null) {
            val cursor = contentResolver.query(uri, null, null, null, null)
            if (cursor != null) {
                cursor.moveToFirst()
                val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                path = cursor.getString(idx)
                cursor.close()
            }
        }

        return path
    }
}