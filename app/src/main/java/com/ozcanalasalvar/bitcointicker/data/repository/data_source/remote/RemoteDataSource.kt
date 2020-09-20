package com.ozcanalasalvar.bitcointicker.data.repository.data_source.remote

import com.ozcanalasalvar.bitcointicker.data.remote.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : RemoteSource {

    override fun getCoins(
        currency: String,
        ids: String?,
        order: String,
        perPage: Int,
        page: Int,
        sparkLine: Boolean,
        unit: String?
    ) = apiService.getCoins(currency, ids, order, perPage, page, sparkLine, unit)

    override fun getCoinList() = apiService.getCoinList()

}