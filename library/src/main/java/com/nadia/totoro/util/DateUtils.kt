package com.nadia.totoro.util

import android.annotation.SuppressLint
import com.nadia.totoro.app.NConstant
import java.text.SimpleDateFormat
import java.util.*

/**
 * 日期工具
 * author: Created by 闹闹 on 2018-10-14
 * version: 1.0.0
 */
class DateUtils {

	//一天的秒数
	val ONE_DAY_TIME = (24 * 60 * 60).toLong()

	/**
	 * 获取年月日时间
	 */
	@SuppressLint("SimpleDateFormat")
	fun getNYYMMDD(): String {
		val sdf = SimpleDateFormat(NConstant.YYYYMMDD)
		return sdf.format(Date())
	}

	/**
	 * 获取年月日时间
	 */
	@SuppressLint("SimpleDateFormat")
	fun getNYYMMDD(day: Int): String {
		val sdf = SimpleDateFormat(NConstant.YYYYMMDD)
		val date = Date()
		val now = date.time
		val next = now + day.toLong() * ONE_DAY_TIME * 1000
		return sdf.format(next)
	}
}

