package com.nadia.totoro.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

/**
 *系统工具
 * author: Created by 闹闹 on 2019/1/22
 * version: 1.0.0
 */
class SystemUtils(val context: Context) {

	/**
	 * 复制内容到剪切板
	 *
	 * @param copyStr
	 * @return
	 */
	fun copy(copyStr: String): Boolean {
		return try {
			//获取剪贴板管理器
			val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
			// 创建普通字符型ClipData
			val mClipData = ClipData.newPlainText("Label", copyStr)
			// 将ClipData内容放到系统剪贴板里。
			cm.primaryClip = mClipData
			true
		} catch (e: Exception) {
			false
		}
	}

	/**
	 * 判断微信是否安装
	 *
	 */
	fun isWeixinAvilible(): Boolean {
		val packageManager = context.packageManager
		val info = packageManager.getInstalledPackages(0)
		if (info != null) {
			for (i in info.indices) {
				val pn = info[i].packageName
				if (pn == "com.tencent.mm") {
					return true
				}
			}
		}
		return false
	}
}