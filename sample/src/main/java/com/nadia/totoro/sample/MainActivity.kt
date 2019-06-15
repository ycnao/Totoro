package com.nadia.totoro.sample

import android.os.Bundle
import com.nadia.totoro.sample.presenter.TestPresenter
import com.nadia.totoro.sample.view.TestView
import kotlinx.android.synthetic.main.activity_main.*

/**
 *
 * author: Created by 闹闹 on 2019/6/14
 * version: 1.0.0
 */
class MainActivity : BaseActivity<MainActivity>(), TestView {

	private val presenter = TestPresenter()

	override fun initLayout(): Int = R.layout.activity_main

	override fun initParameter(bundle: Bundle?) {}

	override fun afterInjectView() {
		presenter.attachView(this)
		presenter.login("龙猫框架 测试成功")
	}

	override fun test(data: String) {
		tv_main.text = data
	}

	override fun onDestroy() {
		super.onDestroy()
		presenter.detachView()
	}
}
