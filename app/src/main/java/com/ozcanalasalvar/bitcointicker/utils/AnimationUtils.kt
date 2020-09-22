package com.ozcanalasalvar.bitcointicker.utils

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.Transformation


class AnimationUtils {

    companion object {
        fun expandWithArrow(v: View, turn: View) {
            val matchParentMeasureSpec: Int =
                View.MeasureSpec.makeMeasureSpec((v.parent as View).width, View.MeasureSpec.EXACTLY)
            val wrapContentMeasureSpec: Int =
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            v.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
            val targetHeight: Int = v.measuredHeight

            v.layoutParams.height = 1
            v.visibility = View.VISIBLE
            val a: Animation = object : Animation() {
                override fun applyTransformation(
                    interpolatedTime: Float,
                    t: Transformation?
                ) {
                    v.layoutParams.height =
                        if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (targetHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }

                override fun willChangeBounds(): Boolean {
                    return true
                }
            }
            val duration =
                (targetHeight / v.context.resources.displayMetrics.density).toInt()
            a.duration = duration.toLong()
            initialRotate(turn, duration)
            v.startAnimation(a)
        }

        fun collapseWithArrow(v: View, turn: View) {
            val initialHeight: Int = v.measuredHeight
            val a: Animation = object : Animation() {
                override fun applyTransformation(
                    interpolatedTime: Float,
                    t: Transformation?
                ) {
                    if (interpolatedTime == 1f) {
                        v.visibility = View.GONE
                    } else {
                        v.layoutParams.height =
                            initialHeight - (initialHeight * interpolatedTime).toInt()
                        v.requestLayout()
                    }
                }

                override fun willChangeBounds(): Boolean {
                    return true
                }
            }
            val duration =
                (initialHeight / v.context.resources.displayMetrics.density).toInt()
            a.duration = duration.toLong()
            endRotate(turn, duration)
            v.startAnimation(a)
        }

        private fun endRotate(view: View, duration: Int) {
            view.animate()
                .rotation(0f)
                .setDuration(duration.toLong())
                .setInterpolator(LinearInterpolator())
                .start()
        }

        private fun initialRotate(view: View, duration: Int) {
            view.animate()
                .rotation(180f)
                .setDuration(duration.toLong())
                .setInterpolator(LinearInterpolator())
                .start()
        }

    }

}