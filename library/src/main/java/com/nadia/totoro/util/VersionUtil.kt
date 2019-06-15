package com.nadia.totoro.util

import android.content.Context
import android.content.pm.PackageManager

/**
 * 版本工具
 * author: Created by 闹闹 on 2018-10-14
 * version: 1.0.0
 */
class VersionUtil(private val context: Context) {

    /**
     * 获取当前本地apk的版本
     */
    fun getVersionCode(): Int {
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            return context.packageManager.getPackageInfo(context.packageName, 0).versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * 获取版本号名称
     */
    fun getVerName(): String {
        try {
            return context.packageManager.getPackageInfo(context.packageName, 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return ""
    }
}

