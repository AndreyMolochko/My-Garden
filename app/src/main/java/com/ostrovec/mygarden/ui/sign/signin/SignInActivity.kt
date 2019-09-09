package com.ostrovec.mygarden.ui.sign.signin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivitySignInBinding
import com.ostrovec.mygarden.ui.base.BaseNavigationActivity
import com.ostrovec.mygarden.ui.myplants.MyPlantsActivity
import com.ostrovec.mygarden.ui.sign.model.User
import com.ostrovec.mygarden.ui.sign.signup.SignUpActivity

class SignInActivity : BaseNavigationActivity() {

    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, SignInActivity::class.java))
        }
    }

    private val signInHandler: SignInHandler = object : SignInHandler {
        override fun onEmailChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            user.email = p0.toString()
            checkSaveButton()
        }

        override fun onPasswordChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            user.password = p0.toString()
            checkSaveButton()
        }

        override fun onClickSignIn() {
            signInViewModel.signIn(user.email, user.password).addOnCompleteListener {
                if (it.isSuccessful) {
                    MyPlantsActivity.open(this@SignInActivity)
                } else {
                    binding.root.hideKeyboard()
                    if (it.exception != null) {
                        showSnackbar(binding.root,it.exception!!.message.toString())
                    }
                }
            }
        }

        override fun onClickSignUp() {
            SignUpActivity.open(this@SignInActivity)
        }

    }

    private lateinit var binding: ActivitySignInBinding
    private lateinit var signInViewModel: SignInViewModel
    private var user: User = User("", "molochko.andrey@mail.ru", "123456")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_sign_in)
        signInViewModel = getViewModel(SignInViewModel::class.java)
        binding.model = user
        binding.handler = signInHandler
        enableSaveButton(false)
        initSubscribers()
    }

    private fun checkSaveButton() {
        signInViewModel.checkSignInButton(user.email, user.password)
    }

    private fun initSubscribers() {
        signInViewModel.signInButtonClickable.subscribe {
            enableSaveButton(it)
        }
    }

    private fun enableSaveButton(enable: Boolean) {
        binding.signInSignInTextView.isEnabled = enable
    }
}
