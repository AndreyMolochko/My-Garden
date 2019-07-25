package com.ostrovec.mygarden.ui.myplants

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivityMyPlantsBinding
import com.ostrovec.mygarden.ui.base.BaseActivity

class MyPlantsActivity : BaseActivity() {

    companion object{
        fun open(context: Context){
            val intent = Intent(context,MyPlantsActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityMyPlantsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_plants)
    }
}
