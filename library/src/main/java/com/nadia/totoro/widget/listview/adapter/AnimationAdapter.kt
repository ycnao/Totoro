package com.nadia.totoro.widget.listview.adapter

import android.animation.Animator
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import com.nadia.totoro.widget.listview.helper.ViewHelper

/**
 *
 * author: Created by 闹闹 on 2018-09-22
 * version: 1.0.0
 */
abstract class AnimationAdapter(private val mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

	private var mDuration = 300
	private var mLastPosition = -1
	private var isFirstOnly = true
	private var mInterpolator: Interpolator = LinearInterpolator()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return mAdapter.onCreateViewHolder(parent, viewType)
	}

	override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
		super.registerAdapterDataObserver(observer)
		mAdapter.registerAdapterDataObserver(observer)
	}

	override fun unregisterAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
		super.unregisterAdapterDataObserver(observer)
		mAdapter.unregisterAdapterDataObserver(observer)
	}

	override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
		super.onAttachedToRecyclerView(recyclerView)
		mAdapter.onAttachedToRecyclerView(recyclerView)
	}

	override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
		super.onDetachedFromRecyclerView(recyclerView)
		mAdapter.onDetachedFromRecyclerView(recyclerView)
	}

	override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
		super.onViewAttachedToWindow(holder)
		mAdapter.onViewAttachedToWindow(holder)
	}

	override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
		super.onViewDetachedFromWindow(holder)
		mAdapter.onViewDetachedFromWindow(holder)
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		mAdapter.onBindViewHolder(holder, position)

		val adapterPosition = holder.adapterPosition
		if (!isFirstOnly || adapterPosition > mLastPosition) {
			for (anim in getAnimators(holder.itemView)) {
				anim.setDuration(mDuration.toLong()).start()
				anim.interpolator = mInterpolator
			}
			mLastPosition = adapterPosition
		} else {
			ViewHelper.clear(holder.itemView)
		}
	}

	override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
		mAdapter.onViewRecycled(holder)
		super.onViewRecycled(holder)
	}

	override fun getItemCount(): Int = mAdapter.itemCount

	fun setDuration(duration: Int) {
		mDuration = duration
	}

	fun setInterpolator(interpolator: Interpolator) {
		mInterpolator = interpolator
	}

	fun setStartPosition(start: Int) {
		mLastPosition = start
	}

	protected abstract fun getAnimators(view: View): Array<Animator>

	fun setFirstOnly(firstOnly: Boolean) {
		isFirstOnly = firstOnly
	}

	override fun getItemViewType(position: Int): Int = mAdapter.getItemViewType(position)

	fun getWrappedAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder> = mAdapter

	override fun getItemId(position: Int): Long = mAdapter.getItemId(position)
}
