package com.ozcanalasalvar.bitcointicker.ui.search

import androidx.lifecycle.MutableLiveData
import com.ozcanalasalvar.bitcointicker.ui.base.BaseViewModel
import com.ozcanalasalvar.bitcointicker.data.model.SimpleModel
import com.ozcanalasalvar.bitcointicker.data.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    val searchList = MutableLiveData<List<SimpleModel>>()
    val showLoading = MutableLiveData<Boolean>()

    init {
        fetchCoins()
    }

    private fun fetchCoins() {
        disposable.add(
            repository.fetchCoinList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    showLoading.value = true
                }
                .doOnComplete {
                    showLoading.value = false
                }.doOnError {
                    //todo
                }
                .subscribe {
                    searchList.value = it
                }

        )
    }

    fun fetchCoinsByQuery(query: String) {
        disposable.add(
            repository.fetchCoinsByQuery(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    showLoading.value = true
                }
                .doOnComplete {
                    showLoading.value = false
                }
                .doOnError {
                    //todo
                }
                .subscribe {
                    searchList.value = it
                }
        )
    }

    fun addToFavourite(model: SimpleModel) {
        //todo
    }

    fun removeFromFavourite(model: SimpleModel) {
        //todo
    }

}