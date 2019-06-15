package com.nadia.totoro.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.nadia.totoro.app.NConstant

/**
 *权限
 * author: Created by 闹闹 on 2018-09-26
 * version: 1.0.0
 */
class PermissionUtil(val context: Context) {

	// 请求网络权限
	fun requestInternet(): Boolean = requestPermissions(Manifest.permission.INTERNET)

	// 请求拨打电话
	fun requestCallPhone(): Boolean = requestPermissions(Manifest.permission.CALL_PHONE)

	// 请求摄像权限
	fun requestCamera(): Boolean = requestPermissions(Manifest.permission.CAMERA)

	// 存储权限
	fun requestStore(): Boolean = requestPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)

	// 录音权限
	fun requestRecorder(): Boolean = requestPermissions(Manifest.permission.RECORD_AUDIO)

	// 定位权限
	fun requestLocation(): Boolean = requestPermissions(Manifest.permission.ACCESS_COARSE_LOCATION)

	// 读取联系人
	fun requestContact(): Boolean = requestPermissions(Manifest.permission.READ_CONTACTS)

	private fun requestPermissions(permission: String): Boolean {
		when {
			ContextCompat.checkSelfPermission(context as Activity, permission) == PackageManager.PERMISSION_GRANTED -> return true
			ActivityCompat.shouldShowRequestPermissionRationale(context, permission) -> ActivityCompat.requestPermissions(context, arrayOf(permission), NConstant.PERMISSIONS.ALL)
			else -> ActivityCompat.requestPermissions(context, arrayOf(permission), NConstant.PERMISSIONS.ALL)
		}
		return false
	}

	// Support V4 Fragment Activity Permission Result
	fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
		//super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == NConstant.PERMISSIONS.ALL) {
			for (i in permissions.indices) {
				val permission = permissions[i]
				val grantResult = grantResults[i]
				// Permission Type
				if (permission == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
					if (grantResult == PackageManager.PERMISSION_GRANTED) {
						// Agree
					} else {
						// Reject
					}
				}
			}
		}
	}
}
