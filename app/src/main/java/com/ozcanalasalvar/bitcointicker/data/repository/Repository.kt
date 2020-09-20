package com.ozcanalasalvar.bitcointicker.data.repository

import com.ozcanalasalvar.bitcointicker.data.cache.PrefManager
import com.ozcanalasalvar.bitcointicker.data.model.SimpleModel
import com.ozcanalasalvar.bitcointicker.data.repository.data_source.local.LocalDataSource
import com.ozcanalasalvar.bitcointicker.data.repository.data_source.remote.RemoteDataSource
import io.reactivex.Observable
import javax.inject.Inject

class Repository @Inject constructor(
    private val localSource: LocalDataSource,
    private val remoteSource: RemoteDataSource,
    private val prefManager: PrefManager
) {

    fun fetchCoinList(): Observable<List<SimpleModel>> {
        return Observable.concatArray(
            localSource.fetchCoins(),
            remoteSource.getCoinList()
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
    ) = remoteSource.getCoins(currency, ids, order, perPage, page, sparkLine, unit)


}