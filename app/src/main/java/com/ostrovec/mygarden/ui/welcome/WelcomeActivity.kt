package com.ostrovec.mygarden.ui.welcome

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivityWelcomPageBinding
import com.ostrovec.mygarden.ui.base.BaseActivity
import com.ostrovec.mygarden.ui.myplants.MyPlantsActivity
import com.ostrovec.mygarden.ui.sign.signup.SignUpActivity

class WelcomeActivity : BaseActivity() {

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, WelcomeActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val welcomeHandler: WelcomeHandler = object : WelcomeHandler {
        override fun onClickSignIn() {
            MyPlantsActivity.open(this@WelcomeActivity)
        }

        override fun onClickSignUp() {
            SignUpActivity.open(this@WelcomeActivity)
        }
    }

    private lateinit var binding: ActivityWelcomPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcom_page)
        binding.handler = welcomeHandler
    }
}
