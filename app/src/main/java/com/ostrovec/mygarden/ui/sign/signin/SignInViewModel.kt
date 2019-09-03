package com.ostrovec.mygarden.ui.sign.signin

import com.ostrovec.mygarden.repositories.AuthRepository
import com.ostrovec.mygarden.ui.base.BaseViewModel
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class SignInViewModel @Inject constructor(authRepository: AuthRepository): BaseViewModel(){
    val signInButtonClickable = BehaviorSubject.create<Boolean>()

    fun checkSignInButton(email: String, password: String) {
        signInButtonClickable.onNext(email.isNotEmpty() && password
                .isNotEmpty())
    }
}