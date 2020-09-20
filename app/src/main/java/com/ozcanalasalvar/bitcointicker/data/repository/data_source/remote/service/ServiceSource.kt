package com.ozcanalasalvar.bitcointicker.data.repository.data_source.remote.service

import com.ozcanalasalvar.bitcointicker.data.model.DetailModel
import com.ozcanalasalvar.bitcointicker.data.model.SimpleModel
import io.reactivex.Observable

interface ServiceSource {
    fun getCoins(
        currency: String,
        ids: String?,
        order: String,
        perPage: Int,
        page: Int,
        sparkLine: Boolean,
        unit: String?
    ): Observable<List<DetailModel>>

    fun getCoinList(): Observable<List<SimpleModel>>
}