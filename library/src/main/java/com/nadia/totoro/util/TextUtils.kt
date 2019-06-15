package com.nadia.totoro.util

import android.widget.TextView

/**
 *字体工具
 * author: Created by 闹闹 on 2019/1/22
 * version: 1.0.0
 */
class TextUtils(private val tv: TextView) {

	/**
	 * 字体加粗
	 */
	fun setBoldText() {
		val tp = tv.paint
		tp.isFakeBoldText = true
	}

	/**
	 * 字体常规
	 */
	fun setNormalText() {
		val tp = tv.paint
		tp.isFakeBoldText = false
	}
}