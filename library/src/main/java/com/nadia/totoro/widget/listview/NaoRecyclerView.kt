package com.nadia.totoro.widget.listview

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.nadia.totoro.R
import com.nadia.totoro.widget.listview.adapter.SlideInBottomAnimationAdapter

/**
 *
 * author: Created by 闹闹 on 2018-09-22
 * version: 1.0.0
 */
class NaoRecyclerView(context: Context?, attrs: AttributeSet?, defStyle: Int) : RecyclerView(context, attrs, defStyle) {

	interface OnLoadMoreListener {
		/**
		 * 上拉刷新
		 */
		fun loadMoreListener()
	}

	private var footerResource = -1//脚布局
	private var isLoadingMore = false//是否正在加载更多
	private var loadMoreEnable = false//是否允许加载更多
	private var footerVisible = false//脚部是否可以见
	private var loadMoreListener: OnLoadMoreListener? = null//加载数据监听

	constructor(context: Context?) : this(context, null)

	constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

	override fun onScrollStateChanged(newState: Int) {
		super.onScrollStateChanged(newState)
		if (adapter != null && layoutManager != null) {
			val lastVisiblePosition = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
			val itemCount = adapter.itemCount
			/**
			 * 控制下拉刷新回调
			 * itemCount != 0 排除没有数据情况
			 * lastVisiblePosition + 4 >= itemCount - 1 最后可见+4 >= 总条数 加载更多
			 * distanceY < 0 为上拉的时候才刷新
			 */
			if (distanceY < 0 && itemCount != 0 && lastVisiblePosition + 4 >= itemCount - 1 && !isLoadingMore && loadMoreEnable) {
				Log.i("test", "加载更多")
				//正在加载更多
				loading()
				if (footerResource != -1) {//有脚布局
					//显示脚布局
					footerVisible = true
					adapter.notifyItemChanged(itemCount - 1)
				}
				if (loadMoreListener != null) {
					loadMoreListener!!.loadMoreListener()
				}
			}
		}
	}

	/**
	 * 判断滑动方向
	 */
	private var distanceY = 0f
	private var startY = 0f

	override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
		val y = ev.rawY
		when (ev.action) {
			MotionEvent.ACTION_DOWN -> startY = y
			MotionEvent.ACTION_MOVE -> {
				distanceY = y - startY
				startY = y
			}
		}
		return super.dispatchTouchEvent(ev)
	}

	override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
		val slideInBottomAnimationAdapter = SlideInBottomAnimationAdapter(adapter)
		slideInBottomAnimationAdapter.setDuration(600)
		val autoLoadAdapter = AutoLoadAdapter(slideInBottomAnimationAdapter)//添加动画
		super.setAdapter(autoLoadAdapter)
	}

	/**
	 * 设置是否允许加载更多
	 *
	 * @param isEnable
	 */
	fun setLoadMoreEnable(isEnable: Boolean) {
		this.loadMoreEnable = isEnable
	}

	/**
	 * 设置脚布局
	 */
	fun setFooterResource(footerResource: Int) {
		this.footerResource = footerResource
	}

	/**
	 * 加载完成
	 */
	private fun loadMoreComplete() {
		this.isLoadingMore = false
	}

	/**
	 * 正在刷新
	 */
	private fun loading() {
		this.isLoadingMore = true//设置正在刷新
	}

	/**
	 * 加载更多数据回调
	 *
	 * @param listener
	 */
	fun setOnLoadMoreListener(listener: OnLoadMoreListener) {
		this.loadMoreListener = listener
	}

	/**
	 * 刷新数据
	 */
	fun notifyData() {
		if (adapter != null) {
			loadMoreComplete()
			if (footerResource != -1 && loadMoreEnable) {
				//隐藏脚布局
				footerVisible = false
			}
			adapter.notifyDataSetChanged()
		}
	}

	//数据adapter
	inner class AutoLoadAdapter internal constructor(private val dataAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

		private val TYPE_FOOTER = Integer.MAX_VALUE//底部布局

		override fun getItemViewType(position: Int): Int {
			if (position == itemCount - 1 && loadMoreEnable && footerResource != -1 && footerVisible) {
				return TYPE_FOOTER
			}
			if (dataAdapter.getItemViewType(position) == TYPE_FOOTER) {
				throw RuntimeException("adapter中itemType不能为:" + Integer.MAX_VALUE)
			}
			return dataAdapter.getItemViewType(position)
		}

		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
			return if (viewType == TYPE_FOOTER) {//脚部
				FooterViewHolder(LayoutInflater.from(context).inflate(R.layout.item_footer, parent, false))
			} else {//数据
				dataAdapter.onCreateViewHolder(parent, viewType)
			}
		}

		override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
			val itemViewType = getItemViewType(position)
			if (itemViewType != TYPE_FOOTER) {
				dataAdapter.onBindViewHolder(holder, position)
			} else {
				// 设置图标动画
//                val operatingAnim = AnimationUtils.loadAnimation(context, R.anim.loading_more_anim)
				val footerViewHolder = holder as FooterViewHolder
				footerViewHolder.footerImg.visibility = View.GONE
				footerViewHolder.footerText.text = "到底啦"
				footerViewHolder.footerText.setTextColor(context.resources.getColor(R.color.grey31))

//                footerViewHolder.footerImg.startAnimation(operatingAnim)
			}
		}

		override fun getItemCount(): Int {
			if (dataAdapter.itemCount != 0) {
				var count = dataAdapter.itemCount
				if (loadMoreEnable && footerResource != -1 && footerVisible) {
					count++
				}
				return count
			}
			return 0
		}

		internal inner class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
			val footerImg: ImageView = itemView.findViewById(R.id.loadMoreImg)
			val footerText: TextView = itemView.findViewById(R.id.loadMoreHintTv)
		}
	}
}
