package com.ozcanalasalvar.bitcointicker.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ozcanalasalvar.bitcointicker.R
import com.ozcanalasalvar.bitcointicker.data.model.DetailModel
import com.ozcanalasalvar.bitcointicker.databinding.ItemMainLayoutBinding
import com.ozcanalasalvar.bitcointicker.ui.main.MainNavigator

class MainAdapter(private val arrayList: ArrayList<DetailModel>, val navigator: MainNavigator) :
    RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemMainLayoutBinding>(
            inflater,
            R.layout.item_main_layout,
            parent,
            false
        )
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(arrayList[position], navigator)
    }

    fun notifyDataChanges(models: List<DetailModel>) {
        arrayList.clear()
        arrayList.addAll(models)
        notifyDataSetChanged()
    }

}