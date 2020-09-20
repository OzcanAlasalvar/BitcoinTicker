package com.ozcanalasalvar.bitcointicker.di.module

import android.app.Application
import android.content.SharedPreferences
import com.ozcanalasalvar.bitcointicker.App
import com.ozcanalasalvar.bitcointicker.data.cache.SharedPreference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: App) {

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return application
    }


    @Singleton
    @Provides
    fun providesSharedPreferences(): SharedPreferences =
        SharedPreference.getInstanceForDagger(application)
}