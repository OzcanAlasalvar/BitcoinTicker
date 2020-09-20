package com.ozcanalasalvar.bitcointicker.ui.search

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ozcanalasalvar.bitcointicker.R
import com.ozcanalasalvar.bitcointicker.ui.base.BaseActivity
import com.ozcanalasalvar.bitcointicker.data.model.SimpleModel
import com.ozcanalasalvar.bitcointicker.databinding.ActivitySearchBinding
import com.ozcanalasalvar.bitcointicker.ui.detail.DetailActivity
import javax.inject.Inject

class SearchActivity :
    BaseActivity<ActivitySearchBinding, SearchViewModel>(SearchViewModel::class.java),
    SearchNavigator {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val adapter = SearchAdapter(arrayListOf(), this)

    override fun getLayoutRes(): Int = R.layout.activity_search

    override fun getViewModel(): SearchViewModel =
        ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        binding.viewModel = getViewModel()
        binding.navigator = this
        observeLiveData()
    }

    private fun observeLiveData() {
        getViewModel().searchList.observe(this, Observer { list ->
            list?.let {
                adapter.notifyDataChanges(it)
            }
        })
    }

    private fun initViews() {
        binding.searchRecyclerview.adapter = adapter
        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                query?.let {
                    getViewModel().fetchCoinsByQuery(it)
                }
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                binding.searchView.clearFocus()
                return true
            }
        })

        binding.searchView.queryHint = getString(R.string.search_reminder)
        binding.searchView.onActionViewExpanded()
        val searchClose: TextView = binding.searchView.findViewById(R.id.search_src_text)
        val iconX: ImageView = binding.searchView.findViewById(R.id.search_close_btn)
        val iconS: ImageView = binding.searchView.findViewById(R.id.search_mag_icon)
        try {
            searchClose.setTextColor(getColor(R.color.black))
            searchClose.setHintTextColor(getColor(R.color.light_grey))
            searchClose.textSize = 16f
            iconX.setColorFilter(getColor(R.color.black))
            iconS.setColorFilter(Color.parseColor("#000000"))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onInject() {
        getApplicationComponent().inject(this)
    }

    override fun onBackPress() {
        closeSoftKeyboard(binding.searchView)
        finish()
    }

    override fun onItemClick(simpleModel: SimpleModel) {
        startActivity(
            Intent(this, DetailActivity::class.java)
                .putExtra("id", simpleModel.id)
        )
    }

}