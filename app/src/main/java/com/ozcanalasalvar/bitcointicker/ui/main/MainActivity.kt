package com.ozcanalasalvar.bitcointicker.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ozcanalasalvar.bitcointicker.R
import com.ozcanalasalvar.bitcointicker.data.model.DetailModel
import com.ozcanalasalvar.bitcointicker.databinding.ActivityMainBinding
import com.ozcanalasalvar.bitcointicker.ui.base.BaseActivity
import com.ozcanalasalvar.bitcointicker.ui.detail.DetailActivity
import com.ozcanalasalvar.bitcointicker.ui.main.adapter.MainAdapter
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(MainViewModel::class.java),
    MainNavigator {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val adapter = MainAdapter(arrayListOf(), this)
    private lateinit var mHandler: Handler

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun getViewModel(): MainViewModel =
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = getViewModel()
        binding.recyclerViewFav.adapter = adapter
        this.mHandler = Handler()
        this.mHandler.postDelayed(mRunnable, 60000)
        observeLiveData()
    }

    override fun onResume() {
        super.onResume()
        getViewModel().fetchFavourites()
    }

    private fun observeLiveData() {
        getViewModel().favouriteCoins.observe(this, Observer { coins ->
            coins?.let {
                adapter.notifyDataChanges(it)
            }
        })
    }

    override fun onInject() {
        getApplicationComponent().inject(this)
    }

    private val mRunnable: Runnable = object : Runnable {
        override fun run() {
            getViewModel().fetchFavourites().also {
                mHandler.postDelayed(this, 60000)
            }
        }
    }

    override fun onItemClicked(detailModel: DetailModel, v: ImageView) {
        Intent(this, DetailActivity::class.java)
            .putExtra("id", detailModel.id)
            .putExtra("transitionImage", ViewCompat.getTransitionName(v))
            .putExtra("imageUrl", detailModel.image).also {
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this, v,
                    ViewCompat.getTransitionName(v).toString()
                )
                startActivity(it, options.toBundle())
            }
    }

}