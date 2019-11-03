package com.example.loginconmvpsociedadandroide.presentation.passwordRecover.presenter

import com.example.loginconmvpsociedadandroide.domain.interactor.auth.passwordRecoverInteractor.PasswordRecoverInteractor
import com.example.loginconmvpsociedadandroide.presentation.passwordRecover.PasswordRecoverContract
import com.example.loginconmvpsociedadandroide.presentation.passwordRecover.exceptions.PasswordRecoverException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PasswordRecoverPresenter(passwordRecoverInteractor: PasswordRecoverInteractor) :
    PasswordRecoverContract.PasswordRecoverPresenter, CoroutineScope {
    var passwordRecoverInteractor: PasswordRecoverInteractor? = null
    var job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    var view: PasswordRecoverContract.PasswordRecoverView? = null

    init {
        this.passwordRecoverInteractor = passwordRecoverInteractor
    }

    override fun attachView(passwordRecoverView: PasswordRecoverContract.PasswordRecoverView) {
        view = passwordRecoverView
    }

    override fun detachView() {
        view = null
    }

    override fun isViewAttached(): Boolean {
        return view != null
    }

    override fun sendPasswordRecover(email: String) {
        launch {
            try {
                view?.showProgress()
                passwordRecoverInteractor?.sendPasswordResetEmail(email)
                view?.hideProgress()
                view?.navigateToLogin()
            } catch (e: PasswordRecoverException) {
                view?.hideProgress()
                view?.showError(e.message)
            }
        }
    }

    override fun detachJob() {
        coroutineContext.cancel()
    }

}