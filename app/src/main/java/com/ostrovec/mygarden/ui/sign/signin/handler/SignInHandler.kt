package com.ostrovec.mygarden.ui.sign.signin.handler

interface SignInHandler {
    fun onEmailChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)

    fun onPasswordChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)

    fun onClickSignIn()

    fun onClickSignUp()
}