package com.ozcanalasalvar.bitcointicker.di.module

import com.ozcanalasalvar.bitcointicker.data.repository.data_source.local.LocalDataSource
import com.ozcanalasalvar.bitcointicker.data.repository.data_source.remote.service.ServiceDataSource
import com.ozcanalasalvar.bitcointicker.data.repository.Repository
import com.ozcanalasalvar.bitcointicker.data.local.shared.PrefManager
import com.ozcanalasalvar.bitcointicker.data.repository.data_source.remote.firebase.FirebaseSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: LocalDataSource,
        serviceDataSource: ServiceDataSource,
        prefManager: PrefManager,
        firebase: FirebaseSource
    ): Repository {
        return Repository(localDataSource, serviceDataSource, prefManager, firebase)
    }

    @Singleton
    @Provides
    fun provideFirebaseSource(
    ): FirebaseSource {
        return FirebaseSource()
    }

}