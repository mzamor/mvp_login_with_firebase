package com.example.loginconmvpsociedadandroide.presentation.register.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.loginconmvpsociedadandroide.LoginWithMvpApp
import com.example.loginconmvpsociedadandroide.R
import com.example.loginconmvpsociedadandroide.base.BaseActivity
import com.example.loginconmvpsociedadandroide.presentation.home.view.HomeActivity
import com.example.loginconmvpsociedadandroide.presentation.register.SignUpContract
import com.example.loginconmvpsociedadandroide.presentation.register.presenter.SignUpPresenter
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject

class SignUpActivity : BaseActivity(), SignUpContract.RegisterView {
    @Inject
    lateinit var presenter:SignUpPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as LoginWithMvpApp).getAppComponent()?.inject(this)

        presenter.attachView(this)

        bt_register.setOnClickListener {
            signUp()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_register
    }

    override fun navigateToMain() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun signUp() {
        val fullname:String = et_fullname.text.toString().trim()
        val email:String = et_email_register.text.toString().trim()
        val password = et_password_register.text.toString().trim()
        val confirm_password = et_confirm_password_register.text.toString().trim()

        if(presenter.checkEmptyName(fullname)){
            et_fullname.error = "The name is empty"
            return
        }

        if(!presenter.checkValidEmail(email)){
            et_email_register.error = "The email is invalid"
            return
        }

        if(presenter.checkEmptyPasswords(password,confirm_password)){
            et_password_register.error = "Empty field"
            et_confirm_password_register.error = "Empty field"
            return
        }

        if(!presenter.checkPasswordsMatch(password,confirm_password)){
            et_password_register.error = "Password dont match"
            et_confirm_password_register.error = "Password dont match"
            return
        }
        presenter.signUp(fullname,email,password)
    }

    override fun showProgress() {
        progressBar_register.visibility = View.VISIBLE
        bt_register.visibility = View.GONE
    }

    override fun hideProgress() {
        progressBar_register.visibility = View.GONE
        bt_register.visibility = View.VISIBLE
    }

    override fun showError(errorMsg: String?) {
        hideProgress()
        toast(this,errorMsg)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
