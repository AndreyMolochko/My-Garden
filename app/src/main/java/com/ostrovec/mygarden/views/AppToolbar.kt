package com.ostrovec.mygarden.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.ostrovec.mygarden.R

class AppToolbar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr:
Int =
        0) : ConstraintLayout(context, attrs, defStyleAttr) {

    var backButton: ImageButton

    init {
        View.inflate(context, R.layout.toolbar, this)
        backButton = findViewById(R.id.toolbar_back_image_button)
    }

    fun setBackButtonClickListener(clickListener: OnClickListener?) {
        backButton.setOnClickListener(clickListener)
    }
}