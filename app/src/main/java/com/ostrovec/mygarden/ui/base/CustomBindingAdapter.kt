package com.ostrovec.mygarden.ui.base

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ostrovec.mygarden.R
import java.io.ByteArrayOutputStream
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
        Glide.with(imageView.context).asBitmap().load(decodeBitmap(localUrlPhoto)).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("bind:setDrawableImage")
    fun setDrawableImage(imageView: ImageView, drawablePath: Int) {
        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, drawablePath))
    }

    @JvmStatic
    @BindingAdapter("bind:setDropDownIcon")
    fun setDropDownImage(imageView: ImageView, isDropped: Boolean) {
        if (isDropped) {
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, R.drawable.ic_up_arrow))
        } else {
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, R.drawable.ic_drop_down_arrow))
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

    fun encodeBitmap(bitmap: Bitmap): String {
        val qualityImage = 100
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, qualityImage, byteArrayOutputStream)

        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)
    }

    fun decodeBitmap(urlImage: String): Bitmap? {
        val offset = 0
        val decodedByteArray = Base64.decode(urlImage, Base64.DEFAULT)

        return BitmapFactory.decodeByteArray(decodedByteArray, offset, decodedByteArray.size)
    }
}
