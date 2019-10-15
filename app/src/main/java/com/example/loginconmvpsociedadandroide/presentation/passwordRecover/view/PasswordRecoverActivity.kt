package com.example.loginconmvpsociedadandroide.presentation.passwordRecover.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.loginconmvpsociedadandroide.R
import com.example.loginconmvpsociedadandroide.base.BaseActivity
import com.example.loginconmvpsociedadandroide.domain.interactor.auth.passwordRecoverInteractor.PasswordRecoverInteractorImpl
import com.example.loginconmvpsociedadandroide.presentation.login.view.LoginActivity
import com.example.loginconmvpsociedadandroide.presentation.passwordRecover.PasswordRecoverContract
import com.example.loginconmvpsociedadandroide.presentation.passwordRecover.presenter.PasswordRecoverPresenter
import kotlinx.android.synthetic.main.activity_recover_password.*

class PasswordRecoverActivity : BaseActivity(), PasswordRecoverContract.PasswordRecoverView {
    lateinit var presenter: PasswordRecoverContract.PasswordRecoverPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = PasswordRecoverPresenter(PasswordRecoverInteractorImpl())
        presenter.attachView(this)
        btn_recover_password.setOnClickListener {
            recoverPassword()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_recover_password
    }

    override fun showError(msgError: String?) {
        toast(this, msgError)
    }

    override fun showProgress() {
        progressBar_recover_password.visibility = View.GONE
    }

    override fun hideProgress() {
        progressBar_recover_password.visibility = View.VISIBLE
    }

    override fun recoverPassword() {
        var mail = et_recover_password.text.trim().toString()
        if(!mail.isEmpty()) presenter.sendPasswordRecover(mail) else toast(this,"email is empty")
    }

    override fun navigateToLogin() {
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        presenter.detachJob()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detachView()
        presenter.detachJob()
    }


}
