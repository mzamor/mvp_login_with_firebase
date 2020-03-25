package com.example.loginconmvpsociedadandroide

import android.app.Application
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.loginconmvpsociedadandroide.presentation.di.DaggerPresentationComponent
import com.example.loginconmvpsociedadandroide.presentation.di.PresentationComponent
import com.example.loginconmvpsociedadandroide.presentation.di.PresentationModule


class LoginWithMvpApp : Application() {
    private var appComponent : PresentationComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerPresentationComponent.builder().presentationModule(PresentationModule()).build()
        val bundle : Bundle?=Intent().extras
        var message: String? = Intent().getStringExtra("value") 
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun getAppComponent() : PresentationComponent? = appComponent
}