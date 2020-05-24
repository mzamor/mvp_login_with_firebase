package com.example.loginconmvpsociedadandroide.presentation.home.presenter

import com.example.loginconmvpsociedadandroide.domain.interactor.auth.homeInteractor.HomeInteractor
import com.example.loginconmvpsociedadandroide.presentation.home.HomeContract
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HomePresenter(homeInteractor: HomeInteractor) : HomeContract.HomePresenter,
    CoroutineScope {

    var view: HomeContract.HomeView? = null
    var homeInteractor: HomeInteractor? = null

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        this.homeInteractor = homeInteractor
    }

    override fun attachView(homeView: HomeContract.HomeView) {
        this.view = homeView
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

    override fun logOut() {
        launch {
            homeInteractor?.logOut()
            view?.navigateToLogin()
        }
    }
}