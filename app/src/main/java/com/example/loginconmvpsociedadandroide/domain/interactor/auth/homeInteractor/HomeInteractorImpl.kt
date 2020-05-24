package com.example.loginconmvpsociedadandroide.domain.interactor.auth.homeInteractor

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class HomeInteractorImpl : HomeInteractor {
    val firebaseAuth = FirebaseAuth.getInstance()

    override suspend fun logOut() : Unit = suspendCancellableCoroutine { continuation->
        firebaseAuth.signOut()
        continuation.resume(Unit)
    }
}