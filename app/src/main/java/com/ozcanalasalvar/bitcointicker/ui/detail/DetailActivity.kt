package com.ozcanalasalvar.bitcointicker.ui.detail

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ozcanalasalvar.bitcointicker.R
import com.ozcanalasalvar.bitcointicker.databinding.ActivityDetailBinding
import com.ozcanalasalvar.bitcointicker.ui.base.BaseActivity
import com.ozcanalasalvar.bitcointicker.utils.downLoadFromUrl
import javax.inject.Inject


class DetailActivity :
    BaseActivity<ActivityDetailBinding, DetailViewModel>(DetailViewModel::class.java),
    DetailNavigator {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var coinId: String? = null
    private lateinit var mHandler: Handler

    override fun getLayoutRes(): Int = R.layout.activity_detail

    override fun getViewModel(): DetailViewModel =
        ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.navigator = this
        intent.extras?.let {
            coinId = it.getString("id")
            val url = it.getString("imageUrl")
            val imageTransitionName: String? = it.getString("transitionImage")
            binding.imageCoin.transitionName = imageTransitionName
            binding.imageCoin.downLoadFromUrl(url)
        }

        if (!coinId.isNullOrEmpty()) {
            getViewModel().coinId = coinId
            getViewModel().fetchCoinDetail()
        } else {
            finish()
        }
        this.mHandler = Handler()
        this.mHandler.postDelayed(mRunnable, 60000)
        observeLiveData()
    }

    private fun observeLiveData() {
        getViewModel().coin.observe(this, Observer { coin ->
            coin?.let {
                binding.model = it
            }
        })

        getViewModel().networkError.observe(this, Observer { msg ->
            msg?.let {
                showError(it)
            }
        })

        getViewModel().networkSuccess.observe(this, Observer { msg ->
            msg?.let {
                showSucces(it)
            }
        })

        getViewModel().isFavourite.observe(this, Observer { isFav ->
            isFav?.let {
                binding.imgAddFav.setImageResource(if (it) R.drawable.like else R.drawable.dislike)
            }
        })
    }

    override fun onInject() {
        getApplicationComponent().inject(this)
    }

    private val mRunnable: Runnable = object : Runnable {
        override fun run() {
            Toast.makeText(this@DetailActivity, "data updated", Toast.LENGTH_SHORT).show()
            getViewModel().fetchCoinDetail().also {
                mHandler.postDelayed(this, 60000)
            }
        }
    }

    override fun onAddFavourite() {
        coinId?.let { getViewModel().addFavourite() }
    }

    override fun onBackPress() {
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        mHandler.removeCallbacks(mRunnable);
    }


}