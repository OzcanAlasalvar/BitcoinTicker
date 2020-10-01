package com.ozcanalasalvar.bitcointicker.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ozcanalasalvar.bitcointicker.ui.ViewModelProviderFactory
import com.ozcanalasalvar.bitcointicker.ui.auth.AuthViewModel
import com.ozcanalasalvar.bitcointicker.ui.detail.DetailViewModel
import com.ozcanalasalvar.bitcointicker.ui.main.MainViewModel
import com.ozcanalasalvar.bitcointicker.ui.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindsAuthViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindsSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindsDetailViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}