package com.ozcanalasalvar.bitcointicker.ui.auth

import android.content.Intent
import android.util.Patterns
import android.view.View
import com.ozcanalasalvar.bitcointicker.data.repository.Repository
import com.ozcanalasalvar.bitcointicker.ui.auth.login.LoginActivity
import com.ozcanalasalvar.bitcointicker.ui.auth.signup.SignUpActivity
import com.ozcanalasalvar.bitcointicker.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthViewModel(private val repository: Repository) : BaseViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    val user by lazy {
        repository.currentUser()
    }

    fun login() {
        if (!isUserEmailValid(email)) {
            authListener?.onFailure("Please type valid email")
            return
        }

        if (!isPasswordValid(password)) {
            authListener?.onFailure("Please type valid password")
            return
        }
        authListener?.onStarted()

        disposable.add(
            repository.login(email!!, password!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //sending a success callback
                    authListener?.onSuccess()
                }, {
                    //sending a failure callback
                    authListener?.onFailure(it.message!!)
                })
        )
    }

    fun signup() {
        if (!isUserEmailValid(email)) {
            authListener?.onFailure("Please type valid email")
            return
        }

        if (!isPasswordValid(password)) {
            authListener?.onFailure("Please type valid password")
            return
        }
        authListener?.onStarted()
        disposable.add(
            repository.register(email!!, password!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    authListener?.onSuccess()
                }, {
                    authListener?.onFailure(it.message!!)
                })
        )
    }

    fun goToSignUp(view: View) {
        Intent(view.context, SignUpActivity ::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun goToLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    private fun isUserEmailValid(email: String?): Boolean {
        if (email != null) {
            return if (email.contains('@')) {
                Patterns.EMAIL_ADDRESS.matcher(email).matches()
            } else {
                return email.isNotBlank()
            }
        } else {
            return false
        }
    }

    private fun isPasswordValid(password: String?): Boolean {
        return if (password != null) {
            password.length > 5
        } else
            false
    }

}