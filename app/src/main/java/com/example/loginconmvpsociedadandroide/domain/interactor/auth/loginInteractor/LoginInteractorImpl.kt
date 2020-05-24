package com.example.loginconmvpsociedadandroide.domain.interactor.auth.loginInteractor

import com.example.loginconmvpsociedadandroide.presentation.login.exceptions.FirebaseLoginException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class LoginInteractorImpl : LoginInterator {
    val firebaseAuth = FirebaseAuth.getInstance()

    override suspend fun loginWithEmailAndPassword(email: String, password: String): Unit =
        suspendCancellableCoroutine { continuation ->
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    continuation.resume(Unit)
                } else {
                    continuation.resumeWithException(FirebaseLoginException(it.exception?.message))
                }
            }
        }

    override suspend fun isLoggged(): Unit = suspendCancellableCoroutine { continuation ->
        if (firebaseAuth.currentUser != null) {
            continuation.resume(Unit)
        } else {
            continuation.resumeWithException(FirebaseLoginException("no esta loggeado"))
        }
    }
}