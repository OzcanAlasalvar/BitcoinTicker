package com.ozcanalasalvar.bitcointicker

import android.app.Application
import com.ozcanalasalvar.bitcointicker.di.component.AppComponent
import com.ozcanalasalvar.bitcointicker.di.component.DaggerAppComponent
import com.ozcanalasalvar.bitcointicker.di.module.ApplicationModule
import com.ozcanalasalvar.bitcointicker.di.module.DBModule

class App : Application() {
    private lateinit var component: AppComponent

    companion object {
        lateinit var INSTANCE: App
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        component = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .dBModule(DBModule(this))
            .build()
        component.inject(this)
    }

    fun getAppComponent(): AppComponent {
        return component
    }
}