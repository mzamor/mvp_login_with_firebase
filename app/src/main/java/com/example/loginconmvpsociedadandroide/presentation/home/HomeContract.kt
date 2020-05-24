package com.example.loginconmvpsociedadandroide.presentation.home

interface HomeContract {
    interface HomeView {
        fun navigateToLogin()
    }

    interface HomePresenter {
        fun attachView(homeView: HomeView)
        fun dettachView()
        fun dettachJob()
        fun isViewAttached(): Boolean
        fun logOut()

    }
}