package com.ostrovec.mygarden.ui.sign.signin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivitySignInBinding
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity
import com.ostrovec.mygarden.ui.sign.model.User

class SignInActivity : BaseNavigationActivity() {

    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, SignInActivity::class.java))
        }
    }

    private val signInHandler: SignInHandler = object : SignInHandler{
        override fun onNameChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onEmailChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onClickSignIn() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onClickSignUp() {
            
        }

    }

    private lateinit var binding: ActivitySignInBinding
    private var user: User = User("", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_sign_in)
        binding.model = user
        binding.handler = signInHandler
    }
}
