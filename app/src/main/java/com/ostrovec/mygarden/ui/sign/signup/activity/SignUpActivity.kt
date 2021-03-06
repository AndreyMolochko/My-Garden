package com.ostrovec.mygarden.ui.sign.signup.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ostrovec.mygarden.R
import com.ostrovec.mygarden.databinding.ActivitySignUpBinding
import com.ostrovec.mygarden.ui.base.activity.BaseNavigationActivity
import com.ostrovec.mygarden.ui.myplants.activity.MyPlantsActivity
import com.ostrovec.mygarden.ui.sign.model.User
import com.ostrovec.mygarden.ui.sign.signin.activity.SignInActivity
import com.ostrovec.mygarden.ui.sign.signup.handler.SignUpHandler
import com.ostrovec.mygarden.ui.sign.signup.viewmodel.SignUpViewModel

class SignUpActivity : BaseNavigationActivity() {

    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, SignUpActivity::class.java))
        }
    }

    private val signUpHandler: SignUpHandler = object :
        SignUpHandler {
        override fun onNameChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            user.name = p0.toString()
            checkSaveButton()
        }

        override fun onEmailChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            user.email = p0.toString()
            checkSaveButton()
        }

        override fun onPasswordChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            user.password = p0.toString()
            checkSaveButton()
        }

        override fun onClickSignUp() {
            signUpViewModel.signUp(user.email,user.password).addOnCompleteListener {
                if(it.isSuccessful){
                    MyPlantsActivity.open(this@SignUpActivity)
                }else{
                    binding.root.hideKeyboard()
                    if (it.exception != null) {
                        showSnackbar(binding.root,it.exception!!.message.toString())
                    }
                }
            }
        }

        override fun onClickSignIn() {
            SignInActivity.open(this@SignUpActivity)
        }

    }

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel
    private var user: User = User("", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContainerView(R.layout.activity_sign_up)
        signUpViewModel = getViewModel(SignUpViewModel::class.java)
        binding.handler = signUpHandler
        binding.model = user
        enableSaveButton(false)
        initSubscribers()
    }

    private fun checkSaveButton() {
        signUpViewModel.checkSignUpButton(user.name, user.email, user.password)
    }

    private fun initSubscribers() {
        signUpViewModel.signUpButtonClickable.subscribe {
            enableSaveButton(it)
        }
    }

    private fun enableSaveButton(enable: Boolean) {
        binding.signUpSignUpTextView.isEnabled = enable
    }
}
