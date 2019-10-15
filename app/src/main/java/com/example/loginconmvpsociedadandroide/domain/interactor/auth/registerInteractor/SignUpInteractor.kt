package com.example.loginconmvpsociedadandroide.domain.interactor.auth.registerInteractor

interface SignUpInteractor {
    suspend fun signUp(fullname:String, email:String, password:String)
}