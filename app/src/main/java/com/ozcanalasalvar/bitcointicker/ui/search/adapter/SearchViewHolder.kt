package com.ozcanalasalvar.bitcointicker.ui.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.ozcanalasalvar.bitcointicker.data.model.SimpleModel
import com.ozcanalasalvar.bitcointicker.databinding.SimpleItemLayoutBinding
import com.ozcanalasalvar.bitcointicker.ui.search.SearchNavigator

class SearchViewHolder(private val binding: SimpleItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: SimpleModel, navigator: SearchNavigator) {
        binding.model = model
        binding.navigator = navigator
        binding.executePendingBindings()
    }
}