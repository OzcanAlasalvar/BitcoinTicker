package com.ozcanalasalvar.bitcointicker.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar
import com.ozcanalasalvar.bitcointicker.App
import com.ozcanalasalvar.bitcointicker.R

abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel>(private val mViewModelClass: Class<VM>) :
    AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    lateinit var imm: InputMethodManager

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }

    private lateinit var viewModel: VM

    abstract fun getViewModel(): VM

    private fun initViewModel() {
        this.viewModel = getViewModel()
    }

    //use open keyword to allow child class to override it
    open fun onInject() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        onInject()
        initViewModel()
        super.onCreate(savedInstanceState)

        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.executePendingBindings()
    }


    fun getApplicationComponent() = (application as App).getAppComponent()


    open fun initBinding() {}


    /**
     * closes keyboard for requested view
     */
    open fun closeSoftKeyboard(view: View) {
        imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * shows keyboard for requested view
     */
    open fun showSoftKeyboard() {
        if (!imm.isAcceptingText) {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
        }
    }

    fun showError(message: String) {
        val snack = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        snack.view.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
        snack.show()
    }

    fun showSucces(message: String) {
        val snack = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        snack.view.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
        snack.show()
    }
}