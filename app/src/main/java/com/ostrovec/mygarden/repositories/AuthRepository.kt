package com.ostrovec.mygarden.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthRepository {
    fun isCurrentUser(): Boolean

    fun signUpUser(email: String, password: String): Task<AuthResult>

    fun signInUser(email: String, password: String): Task<AuthResult>
}