package com.example.loginconmvpsociedadandroide.presentation.di

import com.example.loginconmvpsociedadandroide.domain.interactor.auth.loginInteractor.LoginInteractorImpl
import com.example.loginconmvpsociedadandroide.domain.interactor.auth.loginInteractor.LoginInterator
import com.example.loginconmvpsociedadandroide.presentation.login.presenter.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {
    @Provides
    fun provideLoginToPresenter(loginInteractor : LoginInterator) : LoginPresenter = LoginPresenter(loginInteractor)

    @Provides
    fun provideLogin() : LoginInterator = LoginInteractorImpl()
}