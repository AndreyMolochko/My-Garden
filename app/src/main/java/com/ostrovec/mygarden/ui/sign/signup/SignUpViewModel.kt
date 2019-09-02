package com.ostrovec.mygarden.ui.sign.signup

import com.ostrovec.mygarden.ui.base.BaseViewModel
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class SignUpViewModel @Inject constructor(): BaseViewModel(){
    val signUpButtonClickable = BehaviorSubject.create<Boolean>()

    fun checkSignUpButton(name: String, email: String, password: String) {
        signUpButtonClickable.onNext(name.isNotEmpty() && email.isNotEmpty() && password
                .isNotEmpty())
    }
}