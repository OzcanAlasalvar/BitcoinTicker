package com.ozcanalasalvar.bitcointicker.data.remote

import com.ozcanalasalvar.bitcointicker.data.model.DetailModel
import com.ozcanalasalvar.bitcointicker.data.model.SimpleModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val MARKET_COINS = "coins/markets"
        const val LIST_COINS = "coins/list"

        const val CURRENCY = "vs_currency"
        const val IDS = "ids"
        const val ORDER = "order"
        const val PER_PAGE = "per_page"
        const val PAGE = "page"
        const val SPARK_LINE = "sparkline"
        const val PRICE_CHANGE_UNIT = "price_change_percentage"
    }

    @GET(MARKET_COINS)
    fun getCoins(
        @Query(CURRENCY) currency: String,
        @Query(IDS) ids: String?,
        @Query(ORDER) order: String,
        @Query(PER_PAGE) perPage: Int,
        @Query(PAGE) page: Int,
        @Query(SPARK_LINE) sparkLine: Boolean,
        @Query(PRICE_CHANGE_UNIT) unit: String?
    ): Observable<List<DetailModel>>

    @GET(LIST_COINS)
    fun getCoinList(): Observable<List<SimpleModel>>

}