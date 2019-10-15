package com.example.loginconmvpsociedadandroide.domain.interactor.auth.loginInteractor

import com.example.loginconmvpsociedadandroide.presentation.login.exceptions.FirebaseLoginException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class LoginInteractorImpl : LoginInterator {
    override suspend fun loginWithEmailAndPassword(email: String, password: String) : Unit = suspendCancellableCoroutine { continuation ->
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful) {
                continuation.resume(Unit)
            }else{
                continuation.resumeWithException(FirebaseLoginException(it.exception?.message))
            }
        }
    }
}