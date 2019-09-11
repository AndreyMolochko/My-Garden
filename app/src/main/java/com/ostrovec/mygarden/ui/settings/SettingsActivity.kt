package com.ostrovec.mygarden.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivitySettingsBinding
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity

class SettingsActivity : BaseNavigationActivity() {

    companion object{
        fun open(context: Context){
            context.startActivity(Intent(context,SettingsActivity::class.java))
        }
    }

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_settings)
    }
}
