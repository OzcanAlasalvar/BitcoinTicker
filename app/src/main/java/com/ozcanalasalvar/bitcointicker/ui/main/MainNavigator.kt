package com.ozcanalasalvar.bitcointicker.ui.main

import com.ozcanalasalvar.bitcointicker.data.model.DetailModel

interface MainNavigator {
    fun onItemClicked(detailModel: DetailModel)
}