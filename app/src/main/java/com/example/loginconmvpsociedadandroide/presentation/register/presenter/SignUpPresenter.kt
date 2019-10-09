package com.example.loginconmvpsociedadandroide.presentation.register.presenter

import androidx.core.util.PatternsCompat
import com.example.loginconmvpsociedadandroide.domain.interactor.registerInteractor.SignUpInteractor
import com.example.loginconmvpsociedadandroide.presentation.register.SignUpContract
import com.example.loginconmvpsociedadandroide.presentation.register.exceptions.FirebaseRegisterException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SignUpPresenter(signUpInteractor: SignUpInteractor) : SignUpContract.RegisterPresenter, CoroutineScope {
    private val job  = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    var view : SignUpContract.RegisterView? = null
    var signUpInteractor:SignUpInteractor? = null

    init {
        this.signUpInteractor=signUpInteractor
    }

    override fun attachView(view: SignUpContract.RegisterView) {
        this.view = view
    }

    override fun isViewAttached(): Boolean {
        return view!=null
    }

    override fun detachView() {
        view=null
    }

    override fun dettachJob() {
        coroutineContext.cancel()
    }

    override fun checkEmptyName(fullName: String): Boolean {
        return fullName.isEmpty()
    }

    override fun checkValidEmail(email: String):Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun checkEmptyPasswords(pw1: String, pw2: String): Boolean {
        return pw1.isEmpty() or pw2.isEmpty()
    }

    override fun checkPasswordsMatch(pw1: String, pw2: String): Boolean {
        return pw1==pw2
    }

    override fun signUp(fullName: String, email: String, password: String) {
        launch{
            view?.showProgress()
           try{
               signUpInteractor?.signUp(fullName,email,password)
               if(isViewAttached()) {
                   view?.navigateToMain()
                   view?.hideProgress()
               }
           }catch (e:FirebaseRegisterException){
               if(isViewAttached()) {
                   view?.showError(e.message)
                   view?.hideProgress()
               }
           }
        }
    }
}