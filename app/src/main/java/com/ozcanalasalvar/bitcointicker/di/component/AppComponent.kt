package com.ozcanalasalvar.bitcointicker.di.component

import android.app.Application
import com.ozcanalasalvar.bitcointicker.di.module.ApplicationModule
import com.ozcanalasalvar.bitcointicker.di.module.DBModule
import com.ozcanalasalvar.bitcointicker.di.module.NetworkModule
import com.ozcanalasalvar.bitcointicker.di.module.RepositoryModule
import com.ozcanalasalvar.bitcointicker.App
import com.ozcanalasalvar.bitcointicker.ui.detail.DetailActivity
import com.ozcanalasalvar.bitcointicker.ui.search.SearchActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, DBModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(application: App)

    fun inject(searchActivity: SearchActivity)

    fun inject(detailActivity: DetailActivity)

    fun application(): Application

}