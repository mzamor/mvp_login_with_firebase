package com.example.loginconmvpsociedadandroide.domain.interactor.registerInteractor

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class SignUpInteractorImpl : SignUpInteractor {
    override fun signUp(
        fullname: String,
        email: String,
        password: String,
        listener: SignUpInteractor.RegisterCallback
    ) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful){
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(fullname)
                    .build()

                FirebaseAuth.getInstance().currentUser?.updateProfile(profileUpdates)?.addOnCompleteListener { itUpdate->
                    if(itUpdate.isSuccessful){
                        listener.onRegisterSuccess()
                    }
                }
            } else{
                listener.onRegisterError(it.exception?. message.toString())
            }
        }
    }
}