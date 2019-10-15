package com.example.loginconmvpsociedadandroide.presentation.login

interface LoginContract {
    interface LoginView {
        fun showError(msgError: String?)
        fun showProgressDialog()
        fun hideProgressDialog()
        fun signIn()
        fun navigateToMain()
        fun navigateToRegister()
        fun navigateToPasswordRecover()
    }

    interface LoginPresenter {
        fun attachView(loginView: LoginView)
        fun dettachView()
        fun dettachJob()
        fun isViewAttached(): Boolean
        fun loginUserWithEmailAndPassword(email: String, password: String)
        fun checkEmptyFields(email: String, password: String): Boolean

    }
}