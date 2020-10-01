package com.ozcanalasalvar.bitcointicker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ozcanalasalvar.bitcointicker.data.repository.Repository
import com.ozcanalasalvar.bitcointicker.ui.detail.DetailViewModel
import com.ozcanalasalvar.bitcointicker.ui.auth.AuthViewModel
import com.ozcanalasalvar.bitcointicker.ui.main.MainViewModel
import com.ozcanalasalvar.bitcointicker.ui.search.SearchViewModel
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class ViewModelProviderFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}

