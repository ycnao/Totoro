package com.nadia.totoro.widget.listview.adapter

import android.animation.Animator
import android.animation.ObjectAnimator
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 *
 * author: Created by 闹闹 on 2018-09-22
 * version: 1.0.0
 */
class SlideInBottomAnimationAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) : AnimationAdapter(adapter) {

    override fun getAnimators(view: View): Array<Animator> {
        val measuredHeight = view.measuredHeight
        return arrayOf(ObjectAnimator.ofFloat(view, "translationY", measuredHeight.toFloat(), 0f))
    }
}
