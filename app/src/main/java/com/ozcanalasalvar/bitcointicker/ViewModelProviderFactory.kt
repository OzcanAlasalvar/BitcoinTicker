package com.ozcanalasalvar.bitcointicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ozcanalasalvar.bitcointicker.data.repository.Repository
import com.ozcanalasalvar.bitcointicker.ui.detail.DetailViewModel
import com.ozcanalasalvar.bitcointicker.ui.auth.AuthViewModel
import com.ozcanalasalvar.bitcointicker.ui.search.SearchViewModel
import javax.inject.Inject

class ViewModelProviderFactory @Inject constructor(private val repository: Repository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java))
            return SearchViewModel(repository) as T
        else if (modelClass.isAssignableFrom(DetailViewModel::class.java))
            return DetailViewModel(repository) as T
        else if (modelClass.isAssignableFrom(AuthViewModel::class.java))
            return AuthViewModel(repository) as T
        else
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name);
    }
}

