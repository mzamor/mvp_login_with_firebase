package com.example.loginconmvpsociedadandroide.presentation.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.loginconmvpsociedadandroide.LoginWithMvpApp
import com.example.loginconmvpsociedadandroide.R
import com.example.loginconmvpsociedadandroide.base.BaseActivity
import com.example.loginconmvpsociedadandroide.presentation.login.LoginContract
import com.example.loginconmvpsociedadandroide.presentation.login.presenter.LoginPresenter
import com.example.loginconmvpsociedadandroide.presentation.main.view.MainActivity
import com.example.loginconmvpsociedadandroide.presentation.passwordRecover.view.PasswordRecoverActivity
import com.example.loginconmvpsociedadandroide.presentation.register.view.SignUpActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.LoginView {
    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as LoginWithMvpApp).getAppComponent()?.inject(this)
        presenter.attachView(this)
        btn_login.setOnClickListener {
            signIn()
        }
        tv_not_an_accouunt_yet_register.setOnClickListener {
            navigateToRegister()
        }

        tv_recover_password.setOnClickListener {
            navigateToPasswordRecover()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun showError(msgError: String?) {
        hideProgressDialog()
        toast(this, msgError)
    }

    override fun showProgressDialog() {
        progressBar_login.visibility = View.VISIBLE
        btn_login.visibility = View.INVISIBLE
    }

    override fun hideProgressDialog() {
        progressBar_login.visibility = View.GONE
        btn_login.visibility = View.VISIBLE
    }

    override fun signIn() {
        val email = et_email.text.toString().trim()
        val password = et_password.text.toString().trim()
        if (presenter.checkEmptyFields(email, password)) {
            toast(this, "uno o dos campos estan vacios")
        } else {
            presenter.loginUserWithEmailAndPassword(email, password)
        }
    }

    override fun navigateToMain() {
        var intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun navigateToRegister() {
        startActivity(Intent(this, SignUpActivity::class.java) )
    }

    override fun navigateToPasswordRecover() {
        startActivity(Intent(this, PasswordRecoverActivity::class.java))
        finish()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.dettachView()
        presenter.dettachJob()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettachView()
        presenter.dettachJob()
    }
}
