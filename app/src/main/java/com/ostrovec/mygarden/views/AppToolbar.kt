package com.ostrovec.mygarden.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import com.ostrovec.mygarden.R

class AppToolbar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr:
Int =
        0) : FrameLayout(context, attrs, defStyleAttr) {

    var backButton: ImageButton
    var settingsButton: ImageButton

    init {
        View.inflate(context, R.layout.toolbar, this)
        backButton = findViewById(R.id.toolbar_back_image_button)
        settingsButton = findViewById(R.id.toolbar_settings_image_button)
    }

    fun setBackButtonClickListener(clickListener: OnClickListener?) {
        backButton.setOnClickListener(clickListener)
    }

    fun setVisibleSettingsButton(){
        settingsButton.visibility = View.VISIBLE
    }

    fun setSettingsButtonListener(clickListener: OnClickListener?){
        settingsButton.setOnClickListener(clickListener)
    }
}