package com.ozcanalasalvar.bitcointicker.ui.search

import com.ozcanalasalvar.bitcointicker.data.model.SimpleModel

interface SearchNavigator {

    fun onBackPress()

    fun onItemClick(simpleModel: SimpleModel)

}