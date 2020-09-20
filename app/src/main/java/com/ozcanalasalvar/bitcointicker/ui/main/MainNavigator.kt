package com.ozcanalasalvar.bitcointicker.ui.main

import android.widget.ImageView
import com.ozcanalasalvar.bitcointicker.data.model.DetailModel

interface MainNavigator {
    fun onItemClicked(detailModel: DetailModel, v: ImageView)
}