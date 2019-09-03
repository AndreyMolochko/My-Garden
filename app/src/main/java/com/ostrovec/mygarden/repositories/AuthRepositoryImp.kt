package com.ostrovec.mygarden.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthRepositoryImp : AuthRepository {
    private val authFirebase: FirebaseAuth = FirebaseAuth.getInstance()

    override fun isCurrentUser(): Boolean {
        return authFirebase.currentUser != null
    }

    override fun signUpUser(email: String, password: String): Task<AuthResult> {
        return authFirebase.createUserWithEmailAndPassword(email,password)
    }

    override fun signInUser(email: String, password: String): Task<AuthResult> {
        return authFirebase.signInWithEmailAndPassword(email,password)
    }

}