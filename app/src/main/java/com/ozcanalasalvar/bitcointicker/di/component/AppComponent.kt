package com.ozcanalasalvar.bitcointicker.di.component

import android.app.Application
import com.ozcanalasalvar.bitcointicker.App
import com.ozcanalasalvar.bitcointicker.di.module.*
import com.ozcanalasalvar.bitcointicker.ui.detail.DetailActivity
import com.ozcanalasalvar.bitcointicker.ui.auth.login.LoginActivity
import com.ozcanalasalvar.bitcointicker.ui.auth.signup.SignUpActivity
import com.ozcanalasalvar.bitcointicker.ui.main.MainActivity
import com.ozcanalasalvar.bitcointicker.ui.search.SearchActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, DBModule::class, RepositoryModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(application: App)

    fun inject(searchActivity: SearchActivity)

    fun inject(detailActivity: DetailActivity)

    fun inject(loginActivity: LoginActivity)

    fun inject(signUpActivity: SignUpActivity)

    fun inject(mainActivity: MainActivity)

    fun application(): Application

}