package com.ostrovec.mygarden.ui.sign.signup.viewmodel

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.ostrovec.mygarden.repositories.AuthRepository
import com.ostrovec.mygarden.ui.base.viewmodel.BaseViewModel
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private var authRepository: AuthRepository):
        BaseViewModel(){
    val signUpButtonClickable = BehaviorSubject.create<Boolean>()

    fun checkSignUpButton(name: String, email: String, password: String) {
        signUpButtonClickable.onNext(name.isNotEmpty() && email.isNotEmpty() && password
                .isNotEmpty())
    }

    fun signUp(email:String, password: String): Task<AuthResult> {
        return authRepository.signUpUser(email,password)
    }
}