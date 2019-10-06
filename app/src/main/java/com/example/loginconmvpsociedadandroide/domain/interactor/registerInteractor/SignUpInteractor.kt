package com.example.loginconmvpsociedadandroide.domain.interactor.registerInteractor

interface SignUpInteractor {
    interface RegisterCallback{
        fun onRegisterSuccess()
        fun onRegisterError(errorMsg:String)
    }
    fun signUp(fullname:String, email:String, password:String, listener:RegisterCallback)
}