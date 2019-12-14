package com.ostrovec.mygarden.ui.sign.signin

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.ostrovec.mygarden.repositories.AuthRepository
import com.ostrovec.mygarden.ui.base.viewmodel.BaseViewModel
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class SignInViewModel @Inject constructor(private var authRepository: AuthRepository) :
        BaseViewModel() {
    val signInButtonClickable = BehaviorSubject.create<Boolean>()

    fun checkSignInButton(email: String, password: String) {
        signInButtonClickable.onNext(email.isNotEmpty() && password
                .isNotEmpty())
    }

    fun signIn(email: String, password: String): Task<AuthResult> {
        return authRepository.signInUser(email, password)
    }
}