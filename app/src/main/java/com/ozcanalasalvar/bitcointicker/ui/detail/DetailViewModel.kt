package com.ozcanalasalvar.bitcointicker.ui.detail

import androidx.lifecycle.MutableLiveData
import com.ozcanalasalvar.bitcointicker.ui.base.BaseViewModel
import com.ozcanalasalvar.bitcointicker.data.model.DetailModel
import com.ozcanalasalvar.bitcointicker.data.model.Favourite
import com.ozcanalasalvar.bitcointicker.data.model.FavouriteDataHolder
import com.ozcanalasalvar.bitcointicker.data.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel(private val repository: Repository) : BaseViewModel() {

    val coin = MutableLiveData<DetailModel>()
    val networkError = MutableLiveData<String>()
    val networkSuccess = MutableLiveData<String>()

    fun fetchCoinDetail(id: String?) {
        disposable.add(
            repository.getCoins("try", id, "market_cap_desc", 1, 1, false, "24h")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError {
                    networkSuccess.value = "List load error"
                }
                .subscribe { data ->
                    data[0].let { model ->
                        coin.value = model
                    }
                }
        )
    }


    fun addFavourite(coinId: String) {
        if (!isExist(coinId)) {
            FavouriteDataHolder.addFavourite(coinId)
            disposable.add(
                repository.saveFavourites("123", Favourite(FavouriteDataHolder.getList()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        networkSuccess.value = "Operation success"
                    }, {
                        networkError.value = "Operation error"
                    })
            )
        } else {
            networkError.value = "Already exist"
        }
    }

    private fun isExist(id: String): Boolean {
        return FavouriteDataHolder.getList().contains(id)
    }

}