package com.ostrovec.mygarden.ui.guide

import android.content.Context
import com.ostrovec.mygarden.ui.welcome.WelcomeActivity

class GuideHandler {

    fun onClickOK(context: Context) {
        WelcomeActivity.open(context)
    }

}