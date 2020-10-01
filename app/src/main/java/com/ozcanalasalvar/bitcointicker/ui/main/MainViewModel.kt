package com.ozcanalasalvar.bitcointicker.ui.main

import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.ozcanalasalvar.bitcointicker.data.model.DetailModel
import com.ozcanalasalvar.bitcointicker.data.model.FavouriteDataHolder
import com.ozcanalasalvar.bitcointicker.ui.base.BaseViewModel
import com.ozcanalasalvar.bitcointicker.data.repository.Repository
import com.ozcanalasalvar.bitcointicker.ui.auth.login.LoginActivity
import com.ozcanalasalvar.bitcointicker.ui.detail.DetailActivity
import com.ozcanalasalvar.bitcointicker.ui.search.SearchActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    val favouriteCoins = MutableLiveData<List<DetailModel>>()
    val loading = MutableLiveData<Boolean>()

    private val user by lazy {
        repository.currentUser()
    }

    init {
        fetchFavourites()
    }

    fun fetchFavourites() {
        loading.value = true
        repository.readFavourites(user!!.uid)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val data = task.result
                    if (data?.arrayList != null) {
                        val list = data.arrayList
                        list?.let { array ->
                            FavouriteDataHolder.saveFavourites(array)
                            FavouriteDataHolder.getIds()?.let {
                                getFavouritesDetails(it, array.size)
                            }
                        }
                    }
                    loading.value = data?.arrayList == null
                }
                loading.value = !task.isSuccessful
            }
    }

    private fun getFavouritesDetails(ids: String, size: Int) {
        disposable.add(
            repository.getCoins("try", ids, "market_cap_desc", size, 1, false, "24h")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError {
                    loading.value = false
                    favouriteCoins.value = null
                }
                .subscribe { data ->
                    favouriteCoins.value = data
                    loading.value = false
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
