package com.ozcanalasalvar.bitcointicker.data.model

import com.google.gson.annotations.SerializedName

data class DetailModel(
    @SerializedName("id") var id: String,
    @SerializedName("symbol") var symbol: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("image") var image: String?,
    @SerializedName("current_price") var currentPrice: Float?,
    @SerializedName("market_cap") var marketCap: Float?,
    @SerializedName("market_cap_rank") var marketCapRank: Int?,
    @SerializedName("fully_diluted_valuation") var fullyDilutedValuation: Float?,
    @SerializedName("total_volume") var totalVolume: Float?,
    @SerializedName("high_24h") var high24h: Float?,
    @SerializedName("low_24h") var low24h: Float?,
    @SerializedName("price_change_24h") var priceChange24h: Float?,
    @SerializedName("price_change_percentage_24h") var priceChangePercentage24h: Float?,
    @SerializedName("market_cap_change_24h") var marketCapChange24h: Float?,
    @SerializedName("market_cap_change_percentage_24h") var marketCapChangePercentage24h: Float?,
    @SerializedName("circulating_supply") var circulatingSupply: Float?,
    @SerializedName("total_supply") var totalSupply: Float?,
    @SerializedName("max_supply") var maxSupply: Float?,
    @SerializedName("ath") var ath: Float?,
    @SerializedName("ath_change_percentage") var athChangePercentage: Float?,
    @SerializedName("ath_date") var athDate: String?,
    @SerializedName("atl") var atl: Float?,
    @SerializedName("atl_change_percentage") var atlChangePercentage: Float?,
    @SerializedName("atl_date") var atlDate: String?,
    @SerializedName("roi") var roi: Any?,
    @SerializedName("last_updated") var lastUpdated: String?,
    @SerializedName("price_change_percentage_24h_in_currency") var priceChangePercentage24hInCurrency: Float?
)