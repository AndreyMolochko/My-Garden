package com.ostrovec.mygarden.ui.updateplant

import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.utils.CalendarWorker

object UpdateBindingAdapter {

    @JvmStatic
    @BindingAdapter("bind:setPeriod")
    fun setPeriod(editText: EditText, milliseconds: Long) {
        if (milliseconds > 0) {
            val text = CalendarWorker.convertMillisecondsInDays(milliseconds).toString() + " " + editText.context.getString(R.string.days)
            editText.setText(text)
        }
    }
}