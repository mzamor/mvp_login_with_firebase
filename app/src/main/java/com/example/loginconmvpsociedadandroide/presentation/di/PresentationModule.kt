package com.example.loginconmvpsociedadandroide.presentation.di

import com.example.loginconmvpsociedadandroide.domain.interactor.auth.homeInteractor.HomeInteractor
import com.example.loginconmvpsociedadandroide.domain.interactor.auth.homeInteractor.HomeInteractorImpl
import com.example.loginconmvpsociedadandroide.domain.interactor.auth.loginInteractor.LoginInteractorImpl
import com.example.loginconmvpsociedadandroide.domain.interactor.auth.loginInteractor.LoginInterator
import com.example.loginconmvpsociedadandroide.domain.interactor.auth.passwordRecoverInteractor.PasswordRecoverInteractor
import com.example.loginconmvpsociedadandroide.domain.interactor.auth.passwordRecoverInteractor.PasswordRecoverInteractorImpl
import com.example.loginconmvpsociedadandroide.domain.interactor.auth.registerInteractor.SignUpInteractor
import com.example.loginconmvpsociedadandroide.domain.interactor.auth.registerInteractor.SignUpInteractorImpl
import com.example.loginconmvpsociedadandroide.presentation.home.presenter.HomePresenter
import com.example.loginconmvpsociedadandroide.presentation.login.presenter.LoginPresenter
import com.example.loginconmvpsociedadandroide.presentation.passwordRecover.presenter.PasswordRecoverPresenter
import com.example.loginconmvpsociedadandroide.presentation.register.presenter.SignUpPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {
    @Provides
    fun provideLoginToPresenter(loginInteractor : LoginInterator) : LoginPresenter = LoginPresenter(loginInteractor)

    @Provides
    fun provideLogin() : LoginInterator = LoginInteractorImpl()

    @Provides
    fun provideSignUpToPresenter(signUpInteractor : SignUpInteractor) : SignUpPresenter = SignUpPresenter(signUpInteractor)

    @Provides
    fun provideSignUp() : SignUpInteractor = SignUpInteractorImpl()

    @Provides
    fun providePasswordRecoverToPresenter(passwordRecoverInteractor : PasswordRecoverInteractor) : PasswordRecoverPresenter = PasswordRecoverPresenter(passwordRecoverInteractor)

    @Provides
    fun providePasswordRecover() : PasswordRecoverInteractor = PasswordRecoverInteractorImpl()


    @Provides
    fun provideHomeToPresenter(homeInteractor : HomeInteractor) : HomePresenter = HomePresenter(homeInteractor)

    @Provides
    fun provideHome() : HomeInteractor = HomeInteractorImpl()


}