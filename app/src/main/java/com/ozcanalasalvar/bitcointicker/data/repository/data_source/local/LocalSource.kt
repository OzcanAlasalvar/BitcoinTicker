package com.ozcanalasalvar.bitcointicker.data.repository.data_source.local

import com.ozcanalasalvar.bitcointicker.data.model.SimpleModel
import io.reactivex.Observable
import io.reactivex.Single


interface LocalSource {

    fun saveAll(cities: List<SimpleModel>)

    fun fetchCoins(): Observable<List<SimpleModel>>

    fun fetchCoinByQuery(query: String): Observable<List<SimpleModel>>

    fun deleteDB()


}