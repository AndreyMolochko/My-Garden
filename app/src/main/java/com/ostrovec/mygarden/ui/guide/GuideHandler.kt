package com.ostrovec.mygarden.ui.guide

import android.content.Context
import com.ostrovec.mygarden.ui.welcome.WelcomeActivity
import com.ostrovec.mygarden.utils.SharedPrefsWoker

class GuideHandler {

    fun onClickOK(context: Context) {
        WelcomeActivity.open(context)
        SharedPrefsWoker(context).setShownGuideActivity(false)
    }

}