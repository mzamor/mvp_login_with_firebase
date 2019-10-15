package com.example.loginconmvpsociedadandroide.domain.interactor.auth.passwordRecoverInteractor

interface PasswordRecoverInteractor {
 suspend fun sendPasswordResetEmail(email:String)
}