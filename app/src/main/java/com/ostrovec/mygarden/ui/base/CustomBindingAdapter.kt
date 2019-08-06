package com.ostrovec.mygarden.ui.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ostrovec.mygarden.R

object CustomBindingAdapter {

    @BindingAdapter("bind:srcCompat")
    fun setImage(imageView: ImageView, text: String) {
        if (text.isNotEmpty()) {
            imageView.setImageResource(R.drawable.ic_check)
        } else {
            imageView.setImageResource(R.drawable.ic_circle)
        }
    }
}