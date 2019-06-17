package com.nadia.totoro.sample.presenter

import com.nadia.totoro.presenter.BasePresenter
import com.nadia.totoro.sample.view.TestView

/**
 * 登录
 * author: Created by 闹闹 on 2018-09-13
 * version: 1.0.0
 */
class TestPresenter : BasePresenter<TestView>() {

	fun data() {
		checkViewAttached()
		val list = mutableListOf("TextView", "ImageView", "ListView", "GridView", "Dialog", "Browser", "TabLayout", "工具包")
		getMvpView()!!.loadData(list)
	}
}
