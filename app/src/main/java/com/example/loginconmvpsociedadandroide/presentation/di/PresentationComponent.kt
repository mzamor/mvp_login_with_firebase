package com.example.loginconmvpsociedadandroide.presentation.di

import com.example.loginconmvpsociedadandroide.presentation.login.view.LoginActivity
import dagger.Component


@Component(modules = [PresentationModule::class])

interface PresentationComponent {
    fun inject(loginActivity: LoginActivity)
}