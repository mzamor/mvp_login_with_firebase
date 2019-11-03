package com.example.loginconmvpsociedadandroide

import android.app.Application
import com.example.loginconmvpsociedadandroide.presentation.di.DaggerPresentationComponent
import com.example.loginconmvpsociedadandroide.presentation.di.PresentationComponent
import com.example.loginconmvpsociedadandroide.presentation.di.PresentationModule


class LoginWithMvpApp : Application() {
    private var appComponent : PresentationComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerPresentationComponent.builder().presentationModule(PresentationModule()).build()
    }

    fun getAppComponent() : PresentationComponent? = appComponent
}