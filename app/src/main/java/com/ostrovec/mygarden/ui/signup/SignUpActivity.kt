package com.ostrovec.mygarden.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivitySignUpBinding
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity

class SignUpActivity : BaseNavigationActivity() {

    companion object{
        fun open(context: Context){
            context.startActivity(Intent(context,SignUpActivity::class.java))
        }
    }

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_sign_up)
    }
}
