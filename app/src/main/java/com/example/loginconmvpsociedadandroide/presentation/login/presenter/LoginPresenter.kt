package com.example.loginconmvpsociedadandroide.presentation.login.presenter

import com.example.loginconmvpsociedadandroide.domain.interactor.auth.loginInteractor.LoginInterator
import com.example.loginconmvpsociedadandroide.presentation.login.LoginContract
import com.example.loginconmvpsociedadandroide.presentation.login.exceptions.FirebaseLoginException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginPresenter(loginInteractor: LoginInterator) : LoginContract.LoginPresenter,
    CoroutineScope {
    var view: LoginContract.LoginView? = null
    var loginInteractor: LoginInterator? = null

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        this.loginInteractor = loginInteractor
    }

    override fun attachView(loginView: LoginContract.LoginView) {
        this.view = loginView
    }

    override fun dettachView() {
        view = null
    }

    override fun dettachJob() {
        coroutineContext.cancel()
    }

    override fun isViewAttached(): Boolean {
        return this.view != null
    }

    override fun loginUserWithEmailAndPassword(email: String, password: String) {
        launch {
            view?.showProgressDialog()
            try {
                loginInteractor?.loginWithEmailAndPassword(email, password)
                if (isViewAttached()) {
                    view?.hideProgressDialog()
                    view?.navigateToMain()
                }
            } catch (e: FirebaseLoginException) {
                if (isViewAttached()) {
                    view?.showError(e.message)
                }
            }
        }
    }

    override fun checkEmptyFields(email: String, password: String): Boolean {
        return email.isEmpty() || password.isEmpty()
    }

}