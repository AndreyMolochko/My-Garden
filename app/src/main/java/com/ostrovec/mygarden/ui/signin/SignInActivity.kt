package com.ostrovec.mygarden.ui.signin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivitySignInBinding
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity

class SignInActivity : BaseNavigationActivity() {

    companion object{
        fun open(context: Context){
            context.startActivity(Intent(context,SignInActivity::class.java))
        }
    }

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_sign_in)
    }
}
