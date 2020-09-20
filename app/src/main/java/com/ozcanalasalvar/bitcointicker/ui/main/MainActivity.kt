package com.ozcanalasalvar.bitcointicker.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ozcanalasalvar.bitcointicker.R
import com.ozcanalasalvar.bitcointicker.databinding.ActivityMainBinding
import com.ozcanalasalvar.bitcointicker.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(MainViewModel::class.java) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = getViewModel()

    }

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun getViewModel(): MainViewModel =
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

    override fun onInject() {
        getApplicationComponent().inject(this)
    }

}