package com.ostrovec.mygarden.ui.base

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ostrovec.mygarden.R
import java.io.File

object CustomBindingAdapter {

    @JvmStatic
    @BindingAdapter("bind:srcCompat")
    fun setImage(imageView: ImageView, text: String?) {
        if (text != null) {
            if (text.isNotEmpty()) {
                imageView.setImageResource(R.drawable.ic_check)
            } else {
                imageView.setImageResource(R.drawable.ic_circle)
            }
        } else {
            imageView.setImageResource(R.drawable.ic_circle)
        }
    }

    @JvmStatic
    @BindingAdapter("bind:srcCompat")
    fun setImage(imageView: ImageView, days: Long) {
        if (days > 0) {
            imageView.setImageResource(R.drawable.ic_check)
        } else {
            imageView.setImageResource(R.drawable.ic_circle)
        }
    }

    @JvmStatic
    @BindingAdapter("bind:setImage")
    fun setPlantImage(imageView: ImageView, localUrlPhoto: String) {
        val bitmap = getBitmapImage(localUrlPhoto)
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap)
        }
    }

    fun getBitmapImage(path: String): Bitmap? {
        val file = File(path)
        return if (file.exists()) {
            BitmapFactory.decodeFile(file.absolutePath)
        } else {
            null
        }
    }
}