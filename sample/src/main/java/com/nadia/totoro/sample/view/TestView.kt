package com.nadia.totoro.sample.view

import com.nadia.totoro.sample.model.BannerData
import com.nadia.totoro.view.IView

/**
 *
 * author: Created by 闹闹 on 2018-09-12
 * version: 1.0.0
 */
interface TestView : IView {

	fun loadData(data: List<String>)

	fun loadView(data: List<BannerData>)
}
