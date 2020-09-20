package com.ozcanalasalvar.bitcointicker.ui.detail

import androidx.lifecycle.MutableLiveData
import com.ozcanalasalvar.bitcointicker.base.BaseViewModel
import com.ozcanalasalvar.bitcointicker.data.model.DetailModel
import com.ozcanalasalvar.bitcointicker.data.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailViewModel(private val repository: Repository) : BaseViewModel() {

    val coin = MutableLiveData<DetailModel>()


    fun fetchCoinDetail(id: String?) {
        disposable.add(
            repository.getCoins("try", id, "market_cap_desc", 1, 1, false, "24h")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { data ->
                    data[0].let { model ->
                        coin.value = model
                    }
                }
        )

    }

}