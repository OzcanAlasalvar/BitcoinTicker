package com.ozcanalasalvar.bitcointicker.di.module

import androidx.lifecycle.ViewModelProvider
import com.ozcanalasalvar.bitcointicker.data.repository.data_source.local.LocalDataSource
import com.ozcanalasalvar.bitcointicker.data.repository.data_source.remote.RemoteDataSource
import com.ozcanalasalvar.bitcointicker.data.repository.Repository
import com.ozcanalasalvar.bitcointicker.ui.ViewModelProviderFactory
import com.ozcanalasalvar.bitcointicker.data.local.shared.PrefManager
import com.ozcanalasalvar.bitcointicker.data.repository.data_source.remote.FirebaseSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        prefManager: PrefManager,
        firebase: FirebaseSource
    ): Repository {
        return Repository(localDataSource, remoteDataSource, prefManager, firebase)
    }

    @Singleton
    @Provides
    fun provideViewModelFactory(repository: Repository): ViewModelProvider.Factory {
        return ViewModelProviderFactory(
            repository
        )
    }


    @Singleton
    @Provides
    fun provideFirebaseSource(
    ): FirebaseSource {
        return FirebaseSource()
    }

}