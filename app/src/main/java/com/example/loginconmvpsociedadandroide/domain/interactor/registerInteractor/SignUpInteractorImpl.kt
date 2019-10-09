package com.example.loginconmvpsociedadandroide.domain.interactor.registerInteractor

import android.net.Uri
import com.example.loginconmvpsociedadandroide.presentation.login.exceptions.FirebaseLoginException
import com.example.loginconmvpsociedadandroide.presentation.register.exceptions.FirebaseRegisterException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class SignUpInteractorImpl : SignUpInteractor {
    override suspend fun signUp(fullname: String, email: String, password: String) : Unit = suspendCancellableCoroutine{ continuation ->
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful){
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(fullname)
                    .build()

                FirebaseAuth.getInstance().currentUser?.updateProfile(profileUpdates)?.addOnCompleteListener { itUpdate->
                    if(itUpdate.isSuccessful){
                        continuation.resume(Unit)
                    } else {
                        continuation.resumeWithException(FirebaseRegisterException(itUpdate.exception?.message))
                    }
                }
            } else{
                continuation.resumeWithException(FirebaseRegisterException(it.exception?.message))
            }
        }
    }
}