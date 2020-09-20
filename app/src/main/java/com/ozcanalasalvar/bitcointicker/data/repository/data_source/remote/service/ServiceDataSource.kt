package com.ozcanalasalvar.bitcointicker.data.repository.data_source.remote.service

import com.ozcanalasalvar.bitcointicker.data.remote.ApiService
import javax.inject.Inject

class ServiceDataSource @Inject constructor(
    private val apiService: ApiService
) : ServiceSource {

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