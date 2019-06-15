package com.nadia.totoro.sample.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import com.nadia.totoro.adapter.NArrayListAdapter
import com.nadia.totoro.sample.R


class MainListAdapter(context: Context, data: List<String>, layoutId: Int) : NArrayListAdapter<String>(context, data, layoutId) {

	override fun getViewHolder(): BaseViewHolder = ViewHolder()

	override fun initView(viewHolder: BaseViewHolder, convertView: View, position: Int) {
		val mViewHolder = viewHolder as ViewHolder
		mViewHolder.name = convertView.findViewById(R.id.tv_name)
	}

	override fun setData(viewHolder: BaseViewHolder, convertView: View, position: Int) {
		if (mData == null || mData.isEmpty()) {
			return
		}
		val mViewHolder = viewHolder as ViewHolder
		val listBean = mData[position]

		mViewHolder.name.text = listBean
	}

	internal class ViewHolder : BaseViewHolder() {
		lateinit var name: TextView
	}
}
