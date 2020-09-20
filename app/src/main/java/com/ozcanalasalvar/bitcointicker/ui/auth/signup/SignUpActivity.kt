package com.ozcanalasalvar.bitcointicker.ui.auth.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ozcanalasalvar.bitcointicker.R
import com.ozcanalasalvar.bitcointicker.databinding.ActivitySignUpBinding
import com.ozcanalasalvar.bitcointicker.ui.auth.AuthListener
import com.ozcanalasalvar.bitcointicker.ui.auth.AuthViewModel
import com.ozcanalasalvar.bitcointicker.ui.base.BaseActivity
import com.ozcanalasalvar.bitcointicker.ui.main.MainActivity
import javax.inject.Inject

class SignUpActivity : BaseActivity<ActivitySignUpBinding, AuthViewModel>(
    AuthViewModel::class.java
),
    AuthListener {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = getViewModel()
        getViewModel().authListener = this
    }

    override fun getLayoutRes(): Int = R.layout.activity_sign_up

    override fun getViewModel(): AuthViewModel = ViewModelProviders.of(this, viewModelFactory).get(
        AuthViewModel::class.java
    )

    override fun onInject() {
        getApplicationComponent().inject(this)
    }

    override fun onStarted() {
        binding.loading.visibility = View.VISIBLE
        binding.login.visibility = View.GONE
    }

    override fun onSuccess() {
        binding.loading.visibility = View.GONE
        binding.login.visibility = View.VISIBLE
        startHomeActivity()
    }

    override fun onFailure(message: String) {
        binding.login.visibility = View.VISIBLE
        binding.loading.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun startHomeActivity() {
        Intent(this, MainActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }
}