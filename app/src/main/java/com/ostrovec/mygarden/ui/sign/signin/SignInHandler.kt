package com.ostrovec.mygarden.ui.sign.signin

interface SignInHandler {
    fun onNameChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)

    fun onEmailChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)

    fun onClickSignIn()

    fun onClickSignUp()
}