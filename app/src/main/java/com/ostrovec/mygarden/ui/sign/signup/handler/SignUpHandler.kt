package com.ostrovec.mygarden.ui.sign.signup.handler

interface SignUpHandler {
    //TODO: replace three methods on one (I used here a bad practise)
    fun onNameChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)

    fun onEmailChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)

    fun onPasswordChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
    
    fun onClickSignUp()
    
    fun onClickSignIn()
}