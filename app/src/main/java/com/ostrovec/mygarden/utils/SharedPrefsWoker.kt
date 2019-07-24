package com.ostrovec.mygarden.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsWoker (private val context: Context){

    private val PREFS_FILENAME = "MyGarden.data"
    private val KEY_SHOW_GUIDE = "show guide"
    private var prefs: SharedPreferences? = null

    init {
        prefs = context.getSharedPreferences(PREFS_FILENAME,Context.MODE_PRIVATE)
    }

    fun setShownGuideActivity(isShown: Boolean){
        val editor = prefs!!.edit()
        editor.putBoolean(KEY_SHOW_GUIDE,isShown)
        editor.apply()
    }

    fun shownGuide():Boolean{
        return prefs!!.getBoolean(KEY_SHOW_GUIDE,true)
    }
}