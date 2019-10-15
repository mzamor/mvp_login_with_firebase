package com.example.loginconmvpsociedadandroide.domain.interactor.auth.passwordRecoverInteractor

import com.example.loginconmvpsociedadandroide.presentation.passwordRecover.exceptions.PasswordRecoverException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class PasswordRecoverInteractorImpl : PasswordRecoverInteractor {
    override suspend fun sendPasswordResetEmail(email: String): Unit =
        suspendCancellableCoroutine { continuation ->
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume(Unit)
                    } else {
                        continuation.resumeWithException(PasswordRecoverException(task.exception?.message))
                    }
                }
        }
}