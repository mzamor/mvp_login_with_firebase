package com.example.loginconmvpsociedadandroide.domain.interactor.loginInteractor

interface LoginInterator {
    suspend fun loginWithEmailAndPassword(email:String, password:String)
}