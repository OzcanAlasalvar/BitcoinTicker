package com.ozcanalasalvar.bitcointicker.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ozcanalasalvar.bitcointicker.R
import com.ozcanalasalvar.bitcointicker.data.model.SimpleModel
import com.ozcanalasalvar.bitcointicker.databinding.SimpleItemLayoutBinding

class SearchAdapter(val list: ArrayList<SimpleModel>, val navigator: SearchNavigator) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(private val binding: SimpleItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: SimpleModel, navigator: SearchNavigator) {
            binding.model = model
            binding.navigator = navigator
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SimpleItemLayoutBinding>(
            inflater,
            R.layout.simple_item_layout,
            parent,
            false
        )
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(list[position], navigator)
    }

    fun notifyDataChanges(models: List<SimpleModel>) {
        list.clear()
        list.addAll(models)
        notifyDataSetChanged()
    }
}