package com.xcjr.lib.util

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import android.util.DisplayMetrics

/**
 * 设备工具类
 * author: Created by 闹闹 on 2018/6/26
 * version: 1.0.0
 */
class DeviceUtils {

	/**
	 * 获取屏幕分辨率
	 */
	fun getWidth(activity: Activity): Int {
		val dm = DisplayMetrics()
		activity.windowManager.defaultDisplay.getMetrics(dm)
		return dm.widthPixels
	}

	fun getHeight(activity: Activity): Int {
		val dm = DisplayMetrics()
		activity.windowManager.defaultDisplay.getMetrics(dm)
		return dm.heightPixels
	}

	//设置竖屏
	fun setScreenPortrait(act: Activity) {
		if (!isOrientationPortrait(act)) act.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
	}

	fun isOrientationPortrait(context: Context): Boolean {
		return context.resources.configuration.orientation == 1
	}

	fun checkNetworkConnection(context: Context): Int {
		var flg: Byte = 0
		val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val activeInfo = connMgr.activeNetworkInfo
		if (activeInfo != null && activeInfo.isConnected) {
			val wifiConnected = activeInfo.type == 1
			val mobileConnected = activeInfo.type == 0
			flg = if (wifiConnected) 2 else if (mobileConnected) 1 else 0
		}
		return flg.toInt()
	}

	fun setFullScreen(act: Activity) {
		hideWindowActionBar(act)
		hideWindowStatesBar(act)
	}

	fun hideWindowActionBar(act: Activity) {
		act.requestWindowFeature(1)
	}

	fun hideWindowStatesBar(act: Activity) {
		act.window.setFlags(1024, 1024)
	}
}
