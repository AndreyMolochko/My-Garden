package com.ostrovec.mygarden.ui.addplant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivityAddPlantBinding
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity

class AddPlantActivity : BaseNavigationActivity() {

    companion object {
        fun open(context: Context){
            val intent = Intent(context,AddPlantActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityAddPlantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_add_plant)
    }
}
