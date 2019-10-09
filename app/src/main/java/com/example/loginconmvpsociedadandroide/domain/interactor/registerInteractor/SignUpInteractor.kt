package com.example.loginconmvpsociedadandroide.domain.interactor.registerInteractor

interface SignUpInteractor {
    suspend fun signUp(fullname:String, email:String, password:String)
}