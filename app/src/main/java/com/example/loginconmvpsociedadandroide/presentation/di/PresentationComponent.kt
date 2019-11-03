package com.example.loginconmvpsociedadandroide.presentation.di

import com.example.loginconmvpsociedadandroide.presentation.login.view.LoginActivity
import com.example.loginconmvpsociedadandroide.presentation.passwordRecover.view.PasswordRecoverActivity
import com.example.loginconmvpsociedadandroide.presentation.register.view.SignUpActivity
import dagger.Component


@Component(modules = [PresentationModule::class])

interface PresentationComponent {
    fun inject(loginActivity: LoginActivity)
    fun inject(signUpActivity:SignUpActivity)
    fun inject(passwordActivity:PasswordRecoverActivity)
}