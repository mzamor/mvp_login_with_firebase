package com.example.loginconmvpsociedadandroide.presentation.register.presenter

import androidx.core.util.PatternsCompat
import com.example.loginconmvpsociedadandroide.domain.interactor.registerInteractor.SignUpInteractor
import com.example.loginconmvpsociedadandroide.presentation.register.SignUpContract

class SignUpPresenter(signUpInteractor: SignUpInteractor) : SignUpContract.RegisterPresenter {
    var view : SignUpContract.RegisterView? = null
    var signUpInteractor:SignUpInteractor? = null

    init {
        this.signUpInteractor=signUpInteractor
    }

    override fun attachView(view: SignUpContract.RegisterView) {
        this.view = view
    }

    override fun isViewAttached(): Boolean {
        return view!=null
    }

    override fun detachView() {
        view=null
    }

    override fun checkEmptyName(fullName: String): Boolean {
        return fullName.isEmpty()
    }

    override fun checkValidEmail(email: String):Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun checkEmptyPasswords(pw1: String, pw2: String): Boolean {
        return pw1.isEmpty() or pw2.isEmpty()
    }

    override fun checkPasswordsMatch(pw1: String, pw2: String): Boolean {
        return pw1==pw2
    }

    override fun signUp(fullName: String, email: String, password: String) {
        view?.showProgress()
        signUpInteractor?.signUp(fullName,email,password,object:SignUpInteractor.RegisterCallback {
            override fun onRegisterSuccess() {
                view?.navigateToMain()
                view?.hideProgress()
            }
            override fun onRegisterError(errorMsg: String) {
                view?.showError(errorMsg)
                view?.hideProgress()
            }
        })
    }


}