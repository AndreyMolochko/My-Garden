package com.ostrovec.mygarden

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ostrovec.mygarden.databinding.ActivityWelcomPageBinding

class MainActivity : AppCompatActivity() {

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
