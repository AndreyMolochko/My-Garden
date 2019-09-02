package com.ostrovec.mygarden.ui.sign.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivitySignUpBinding
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity
import com.ostrovec.mygarden.ui.sign.model.User

class SignUpActivity : BaseNavigationActivity() {

    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, SignUpActivity::class.java))
        }
    }

    private val signUpHandler: SignUpHandler = object : SignUpHandler {
        override fun onNameChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            user.name = p0.toString()
            Log.e("ondata", "setname = ${user.setName}")
        }

        override fun onEmailChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            user.email = p0.toString()
            Log.e("ondata", "setemail = ${user.setEmail}")
        }

        override fun onPasswordChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            user.password = p0.toString()
            Log.e("ondata", "setpassword = ${user.setPassword}")
        }

        override fun onClickSignUp() {

        }

        override fun onClickSignIn() {

        }

    }

    private lateinit var binding: ActivitySignUpBinding
    private var user: User = User("", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_sign_up)
        binding.handler = signUpHandler
        binding.model = user
    }
}
