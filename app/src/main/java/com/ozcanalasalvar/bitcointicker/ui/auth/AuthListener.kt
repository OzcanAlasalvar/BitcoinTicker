package com.ozcanalasalvar.bitcointicker.ui.auth

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}
