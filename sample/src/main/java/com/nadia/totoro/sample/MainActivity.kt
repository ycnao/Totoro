package com.nadia.totoro.sample

import android.os.Bundle
import android.view.View
import com.nadia.totoro.sample.adapter.MainListAdapter
import com.nadia.totoro.sample.model.BannerData
import com.nadia.totoro.sample.presenter.TestPresenter
import com.nadia.totoro.sample.view.TestView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

/**
 *
 * author: Created by 闹闹 on 2019/6/14
 * version: 1.0.0
 */
class MainActivity : BaseActivity<MainActivity>(), TestView {

	private val presenter = TestPresenter()
	private val mListData = ArrayList<String>()
	private lateinit var mAdapter: MainListAdapter

	override fun initLayout(): Int = R.layout.activity_main

	override fun initParameter(bundle: Bundle?) {}

	override fun afterInjectView() {
		presenter.attachView(this)

		val footerView = View.inflate(this, R.layout.item_footer, null)
		listView.addFooterView(footerView)

		mAdapter = MainListAdapter(this, mListData, R.layout.item_main)
		listView.adapter = mAdapter

		presenter.data()
	}

	override fun loadData(data: List<String>) {
		mListData.addAll(data)
		mAdapter.notifyDataSetChanged()
	}

	override fun loadView(data: List<BannerData>) {

	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.detachView()
	}
}
