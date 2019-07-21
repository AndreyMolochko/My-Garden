package com.ostrovec.mygarden

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ostrovec.mygarden.databinding.ActivityWelcomPageBinding
import com.ostrovec.mygarden.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityWelcomPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcom_page)

        initListeners()
    }

    private fun initListeners() {
        binding.welcomePageSignIn.setOnClickListener {
            println("click on the sign in button")
        }

        binding.welcomePageSignUp.setOnClickListener {
            println("click on the sign up button")
        }
    }
}
