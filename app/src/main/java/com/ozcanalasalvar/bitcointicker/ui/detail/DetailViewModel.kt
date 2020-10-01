package com.ozcanalasalvar.bitcointicker.ui.detail

import androidx.lifecycle.MutableLiveData
import com.ozcanalasalvar.bitcointicker.ui.base.BaseViewModel
import com.ozcanalasalvar.bitcointicker.data.model.DetailModel
import com.ozcanalasalvar.bitcointicker.data.model.Favourite
import com.ozcanalasalvar.bitcointicker.data.model.FavouriteDataHolder
import com.ozcanalasalvar.bitcointicker.data.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {

    val coin = MutableLiveData<DetailModel>()
    val networkError = MutableLiveData<String>()
    val networkSuccess = MutableLiveData<String>()
    var isFavourite = MutableLiveData<Boolean>()

    var coinId: String? = null

    private val user by lazy {
        repository.currentUser()
    }

    fun fetchCoinDetail() {
        isFavourite.value = coinId?.let { isExist(it) }
        disposable.add(
            repository.getCoins("try", coinId, "market_cap_desc", 1, 1, false, "24h")
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


    fun addFavourite() {
        coinId?.let { id ->
            if (!isExist(id)) {
                FavouriteDataHolder.addFavourite(id)
                changeFavouriteStatus(false)
            } else {
                FavouriteDataHolder.remove(id)
                changeFavouriteStatus(true)
            }

        }

    }

    private fun changeFavouriteStatus(status: Boolean) {
        disposable.add(
            repository.saveFavourites(user!!.uid, Favourite(FavouriteDataHolder.getList()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isFavourite.value = !status
                    networkSuccess.value = "Operation success"
                }, {
                    isFavourite.value = status
                    networkError.value = "Operation error"
                })
        )
    }

    private fun isExist(id: String): Boolean {
        return FavouriteDataHolder.getList().contains(id)
    }

}