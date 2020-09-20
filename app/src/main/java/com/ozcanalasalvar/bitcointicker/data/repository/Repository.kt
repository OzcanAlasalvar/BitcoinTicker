package com.ozcanalasalvar.bitcointicker.data.repository

import com.ozcanalasalvar.bitcointicker.data.local.shared.PrefManager
import com.ozcanalasalvar.bitcointicker.data.model.Favourite
import com.ozcanalasalvar.bitcointicker.data.model.SimpleModel
import com.ozcanalasalvar.bitcointicker.data.repository.data_source.local.LocalDataSource
import com.ozcanalasalvar.bitcointicker.data.repository.data_source.remote.firebase.FirebaseSource
import com.ozcanalasalvar.bitcointicker.data.repository.data_source.remote.service.ServiceDataSource
import io.reactivex.Observable
import javax.inject.Inject

class Repository @Inject constructor(
    private val localSource: LocalDataSource,
    private val serviceSource: ServiceDataSource,
    private val prefManager: PrefManager,
    private val firebase: FirebaseSource
) {

    fun login(email: String, password: String) = firebase.login(email, password)

    fun register(email: String, password: String) = firebase.register(email, password)

    fun currentUser() = firebase.currentUser()

    fun logout() = firebase.logout()

    fun saveFavourites(userId: String, data: Favourite) = firebase.saveFavourite(userId, data)

    fun readFavourites(userId: String) = firebase.readFavourites(userId)

    fun fetchCoinList(): Observable<List<SimpleModel>> {
        return Observable.concatArray(
            localSource.fetchCoins(),
            serviceSource.getCoinList()
                .doOnNext { cities ->
                    cities?.let {
                        saveCoins(it)
                    }
                }
                .onErrorResumeNext(Observable.empty())
        )

    }

    private fun saveCoins(coins: List<SimpleModel>) {
        localSource.saveAll(coins)
    }

    fun fetchCoinsByQuery(query: String) = localSource.fetchCoinByQuery(query)

    fun getCoins(
        currency: String,
        ids: String?,
        order: String,
        perPage: Int,
        page: Int,
        sparkLine: Boolean,
        unit: String?
    ) = serviceSource.getCoins(currency, ids, order, perPage, page, sparkLine, unit)


}