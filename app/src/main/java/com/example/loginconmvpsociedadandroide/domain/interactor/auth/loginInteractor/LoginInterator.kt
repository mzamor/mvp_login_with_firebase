package com.example.loginconmvpsociedadandroide.domain.interactor.auth.loginInteractor

interface LoginInterator {
    suspend fun loginWithEmailAndPassword(email:String, password:String)
}