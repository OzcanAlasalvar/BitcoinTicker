package com.ozcanalasalvar.bitcointicker.utils

import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


fun View.initialRotate(duration: Int) {
    animate()
        .rotationBy(180f)
        .setDuration(duration.toLong())
        .setInterpolator(LinearInterpolator())
        .start()
}


fun View.endRotate(duration: Int) {
    animate()
        .rotationBy(-180f)
        .setDuration(duration.toLong())
        .setInterpolator(LinearInterpolator())
        .start()
}

@BindingAdapter("android:downloadUrl")
fun loadImage(view: ImageView, url: String?) {
    view.downLoadFromUrl(url)
}

fun ImageView.downLoadFromUrl(url: String?) {
    Glide.with(context).load(url).into(this)
}

@BindingAdapter("android:floatTextFormat")
fun floatTextFormat(textView: TextView, value: Float) {
    textView.text = String.format("%.2f", value)
}


@BindingAdapter("android:timeTextFormat")
fun floatTextFormat(textView: TextView, date: String?) {
    date?.let {
        textView.text = DateUtils.formatDateString(it)
    }
}






