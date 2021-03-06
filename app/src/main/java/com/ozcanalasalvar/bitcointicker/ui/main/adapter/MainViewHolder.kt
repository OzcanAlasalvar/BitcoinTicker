package com.ozcanalasalvar.bitcointicker.ui.main.adapter

import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.ozcanalasalvar.bitcointicker.data.model.DetailModel
import com.ozcanalasalvar.bitcointicker.databinding.ItemMainLayoutBinding
import com.ozcanalasalvar.bitcointicker.ui.main.MainNavigator
import com.ozcanalasalvar.bitcointicker.utils.AnimationUtils

class MainViewHolder(private val binding: ItemMainLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: DetailModel, navigator: MainNavigator) {
        binding.model = model

        binding.expandOrCollapse.setOnClickListener {
            if (binding.moreContainer.visibility == View.GONE) {
                AnimationUtils.expandWithArrow(binding.moreContainer, binding.imageMore)
            } else {
                AnimationUtils.collapseWithArrow(binding.moreContainer, binding.imageMore)
            }
        }
        ViewCompat.setTransitionName(binding.imageCoin, model.name)
        binding.mainContainer.setOnClickListener {
            navigator.onItemClicked(model, binding.imageCoin)
        }
    }
}