package com.ozcanalasalvar.bitcointicker.ui.main

import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.ozcanalasalvar.bitcointicker.data.model.DetailModel
import com.ozcanalasalvar.bitcointicker.ui.base.BaseViewModel
import com.ozcanalasalvar.bitcointicker.data.repository.Repository
import com.ozcanalasalvar.bitcointicker.ui.auth.login.LoginActivity
import com.ozcanalasalvar.bitcointicker.ui.search.SearchActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: Repository) : BaseViewModel() {

    val favouriteCoins = MutableLiveData<List<DetailModel>>()


    fun getFavouritesDetails(ids: String) {
        disposable.add(
            repository.getCoins("try", ids, "market_cap_desc", 30, 1, false, "24h")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { data ->
                    favouriteCoins.value = data
                }
        )
    }

    fun signOut(view: View) {
        repository.logout().also {
            Intent(view.context, LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                view.context.startActivity(it)
            }
        }
    }

    fun search(view: View) {
        Intent(view.context, SearchActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
}
